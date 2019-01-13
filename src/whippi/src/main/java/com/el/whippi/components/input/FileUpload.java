/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.input;

import com.el.whippi.components.AComponent;
import com.el.whippi.feactions.FeValue;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;

/**
 *
 * @author david
 */
public class FileUpload extends AComponent {
    
    private String accept;

    private final FeValue<String> feValue;

    public FileUpload() {
        this.feValue = new FeValue<>("window.fileValue_" + this.getId().replace("-", "_"));
    }
    
    public FileUpload withAccept(String accept) {
        this.accept = accept;
        
        return this;
    }

    public FeValue<String> getFeValue() {
        return feValue;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag input = new HtmlTag("input");
        input.withAttribute("type", "file");
        if (accept != null) {
            input.withAttribute("accept", accept);
        }
        input.withAttribute("style", "width: 100%;");
        input.withAttribute("onchange", "window.Whippi.storeFileUpload(event)");

//        HtmlTag script = new HtmlTag("script");
//        res.withChild(script);
//
//        HtmlText cd = new HtmlText("//<![CDATA[\n"
//                + "window.fileValue_" + this.getId().replace("-", "_") + " = null;\n"
//                + "window.convertFile_" + this.getId().replace("-", "_") + " = function () {\n"
//                + "  var fi = document.getElementById('" + this.getId() + "');\n"
//                + "  if (fi.files.length < 0) {\n"
//                + "    window.fileValue_" + this.getId().replace("-", "_") + " = null;\n"
//                + "    return;\n"
//                + "  }\n"
//                + "  var reader = new FileReader();\n"
//                + "  reader.readAsBinaryString(fi.files[0]);\n"
//                + "  reader.onload = function() {\n"
//                + "    window.fileValue_" + this.getId().replace("-", "_") + " = btoa(reader.result);\n"
//                + "  };\n"
//                + "\n"
//                + "  reader.onerror = function() {\n"
//                + "    console.log('there are some problems');\n"
//                + "  };\n"
//                + "}\n"
//                        + "//]]>\n"
//        , false);
//        script.withChild(cd);

        /*
        uploadFileToServer(event) {
            var file = event.srcElement.files[0];
            console.log(file);
            var reader = new FileReader();
            reader.readAsBinaryString(file);

            reader.onload = function() {
                console.log(btoa(reader.result));
            };
            reader.onerror = function() {
                console.log('there are some problems');
            };
        }
         */
        return input;
    }

}
