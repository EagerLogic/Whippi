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
public final class HtmlCData extends AHtmlElement {
    
    private final String content;

    public HtmlCData(String content) {
        this.content = content;
    }

    @Override
    public void render(StringBuilder sb) {
        sb.append("<![CDATA[").append(this.content).append("]]>\n");
    }
    
}
