/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public class Whippi {
    
    private static final List<APage> pages = new ArrayList<>();
    private static IUncaughtExceptionHandler uncaughtExceptionHandler;
    
    public static void registerPage(APage page) {
        if (page == null) {
            throw new NullPointerException("The page parameter can not be null!");
        }
        pages.add(page);
    }
    
    public static APage findPage(String url, HttpServletRequest req) {
        for (APage page : pages) {
            if (page.match(url, req)) {
                return page;
            }
        }
        return null;
    }

    public static IUncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }

    public static void setUncaughtExceptionHandler(IUncaughtExceptionHandler uncaughtExceptionHandler) {
        Whippi.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }
    
    private Whippi() {}
    
}
