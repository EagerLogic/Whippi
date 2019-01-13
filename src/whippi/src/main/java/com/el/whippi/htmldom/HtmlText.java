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
    private final boolean escape;

    public HtmlText(String text) {
        this(text, true);
    }

    public HtmlText(String text, boolean escape) {
        if (text == null) {
            text = "";
        }
        this.text = text;
        this.escape = escape;
    }

    @Override
    public void render(StringBuilder sb) {
        String t = this.text;
        if (this.escape) {
            t = escapeHtmlText(t);
        }
        sb.append(t).append("\n");
    }
    
}
