/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.htmldom;

/**
 *
 * @author david
 */
public abstract class AHtmlElement {
    
    public abstract void render(StringBuilder sb);
    
    protected String escapeHtmlAttribute(String input) {
        input = input
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\r", " ")
                .replace("\n", " ")
                .replace("\t", " ")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
        return input;
    }
    
    protected String escapeHtmlText(String input) {
        input = input
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
        return input;
    }
    
}
