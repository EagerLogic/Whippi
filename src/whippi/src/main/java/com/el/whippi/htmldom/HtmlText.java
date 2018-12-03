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
public final class HtmlText extends AHtmlElement {
    
    private final String text;

    public HtmlText(String text) {
        if (text == null) {
            text = "";
        }
        this.text = text;
    }

    @Override
    public void render(StringBuilder sb) {
        sb.append(this.escapeHtmlText(this.text)).append("\n");
    }
    
}
