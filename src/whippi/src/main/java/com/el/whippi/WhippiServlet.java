/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

import com.el.whippi.components.AComponent;
import com.el.whippi.htmldom.AHtmlElement;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(name = "WhippiServlet", urlPatterns = {"*.wpi"})
public class WhippiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String method = req.getMethod().toUpperCase();
        if (method.equals("GET")) {
            this.processGet(req, resp);
        } else if (method.equals("POST")) {
            this.processPost(req, resp);
        } else {
            throw new RuntimeException("Unsupported request method: " + method);
        }

    }

    private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();

        APage page = Whippi.findPage(servletPath, req);
        if (page == null) {
            resp.sendError(404);
            return;
        }

        AController<?> controller = page.createController(req, resp);
        if (controller == null) {
            throw new NullPointerException("Page: " + page.getClass().getName() + " returned null as controller!");
        }

        if (controller instanceof AAuthenticatedController) {
            AAuthenticatedController authenticatedController = (AAuthenticatedController) controller;
            try {
                boolean authenticationSuccess = authenticatedController.doAuthenticate(req, resp);
                if (!authenticationSuccess) {
                    resp.setStatus(401);
                    return;
                }
            } catch (RedirectException ex) {
                resp.sendRedirect(ex.getUrl());
                return;
            }
        }

        Object model;
        try {
            model = controller.index();
        } catch (RedirectException ex) {
            resp.sendRedirect(ex.getUrl());
            return;
        }

        ATemplate<Object, ?> template = (ATemplate<Object, ?>) controller.createTemplate();
        AComponent rootComponent = template.render(model);
        AHtmlElement htmlRoot = rootComponent.render();

        StringBuilder sb = new StringBuilder();
        htmlRoot.render(sb);
        String html = sb.toString();

        String feJs = readFeJs(req);

        WhippiGetModel responseModel = new WhippiGetModel(model);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, JsonTypeInfo.As.PROPERTY);
        String modelJson = mapper.writeValueAsString(responseModel);

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html style=\"width: 100%; height: 100%;\">");
            out.println("<head>");
            out.println("<title>" + controller.getTitle() + "</title>");
            out.println("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css\" integrity=\"sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX\" crossorigin=\"anonymous\">");
            out.println("");
            out.println("");
            out.println("");
            out.println("<script>");
            out.println("//<![CDATA[");
            out.println(feJs);
            out.println("//]]>");
            out.println("</script>");

            out.println("<style>");
            out.println("a:hover {text-decoration: none; opacity: 0.6;}");
            out.println(".modal {background-color: rgba(0,0,0, 0.75);}");
            out.println("</style>");

            out.println("</head>");
            out.println("<body style=\"width: 100%; height: 100%;\">");
            out.println("<div id=\"whippiRoot\" style=\"width: 100%; height: 100%;\">");
            out.println(html);
            out.println("</div>");
            out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
                    + "<script src=\"https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js\" integrity=\"sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U\" crossorigin=\"anonymous\"></script>\n"
                    + "<script src=\"https://cdn.rawgit.com/FezVrasta/snackbarjs/1.1.0/dist/snackbar.min.js\"></script>\n"
                    + "<script src=\"https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js\" integrity=\"sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9\" crossorigin=\"anonymous\"></script>\n"
                    + "<script>$(document).ready(function() { $('body').bootstrapMaterialDesign(); Whippi.init(\"" + modelJson.replace("\"", "\\\"") + "\"); });</script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void processPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();

        String postStr = req.getReader().lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, JsonTypeInfo.As.PROPERTY);
        WhippiPostRequest postData = mapper.readValue(postStr, WhippiPostRequest.class);

        APage page = Whippi.findPage(servletPath, req);
        if (page == null) {
            resp.sendError(404);
            return;
        }

        AController<Object> controller = (AController<Object>) page.createController(req, resp);
        if (controller == null) {
            throw new NullPointerException("Page: " + page.getClass().getName() + " returned null as controller!");
        }

        boolean isAuthenticationSuccess = true;
        String redirect = null;
        if (controller instanceof AAuthenticatedController) {
            AAuthenticatedController authenticatedController = (AAuthenticatedController) controller;
            try {
                isAuthenticationSuccess = authenticatedController.doAuthenticate(req, resp);
                if (!isAuthenticationSuccess) {
                    resp.setStatus(401);
                    return;
                }
            } catch (RedirectException ex) {
                redirect = ex.getUrl();
            }
        }

        WhippiPostResponse respData = new WhippiPostResponse();
        Object model = postData.getModel();

        if (redirect == null) {

            controller.init(model, postData.getTitle());

            Method[] methods = controller.getClass().getMethods();
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(postData.getMethodName())) {
                    method = m;
                    break;
                }
            }
            if (method == null) {
                throw new RuntimeException("Can't find method: " + postData.getMethodName() + " in controller: " + controller.getClass().getName());
            }

            try {
                method.invoke(controller, postData.getParams());
            } catch (InvocationTargetException ex) {
                Throwable tex = ex.getTargetException();
                if (tex instanceof RedirectException) {
                    redirect = ((RedirectException) tex).getUrl();
                } else {
                    throw new RuntimeException(tex);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        if (redirect == null) {
            model = controller.getModel();

            ATemplate<Object, ?> template = (ATemplate<Object, ?>) controller.createTemplate();
            AComponent rootComponent = template.render(model);
            AHtmlElement htmlRoot = rootComponent.render();

            StringBuilder sb = new StringBuilder();
            htmlRoot.render(sb);
            String html = sb.toString();

            respData.setHtml(html);
            respData.setModel(model);
            respData.setTitle(controller.getTitle());
        } else {
            respData.setRedirect(redirect);
        }

        String responseJson = mapper.writeValueAsString(respData);

        resp.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(responseJson);
        }
    }

    private String readFeJs(HttpServletRequest req) {
        InputStream is = this.getClass().getResourceAsStream("/com/el/whippi/fe/whippi_fe.js");
        //is = req.getServletContext().getResourceAsStream("/com/el/whippi/fe/whippi_fe.js");

        if (is == null) {
            throw new RuntimeException("Can't find Whippi frontend js!");
        }
        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String res = "";
            String line = br.readLine();
            while (line != null) {
                res += line + "\n";
                line = br.readLine();
            }
            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
