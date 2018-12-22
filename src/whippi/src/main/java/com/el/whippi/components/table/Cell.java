/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.table;

import com.el.whippi.components.AComponent;
import com.el.whippi.components.EHAlign;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;

/**
 *
 * @author david
 */
public final class Cell {

    private AComponent content;
    private EHAlign hAlign = EHAlign.LEFT;
    private String width;
    private boolean head;

    public AComponent getContent() {
        return content;
    }

    public Cell withContent(AComponent content) {
        this.content = content;

        return this;
    }

    public EHAlign getHAlign() {
        return hAlign;
    }

    public Cell withHAlign(EHAlign hAlign) {
        if (hAlign == null) {
            throw new NullPointerException();
        }
        this.hAlign = hAlign;

        return this;
    }

    public String getWidth() {
        return width;
    }

    public Cell withWidth(String width) {
        this.width = width;

        return this;
    }

    public Cell withHead(boolean head) {
        this.head = head;

        return this;
    }

    public AHtmlElement render(String scope) {
        String element = "td";
        if (this.head) {
            element = "th";
        }

        HtmlTag res = new HtmlTag(element);
        if (scope != null) {
            res.withAttribute("scope", scope);
        } else if (this.head) {
            res.withAttribute("scope", "row");
        }

        String style = "";

        if (this.width != null) {
            style = style + "width: " + this.width + ";";
        }

        style += "text-aligh: " + this.hAlign.getCssValue() + ";";
        
        res.withAttribute("style", style);

        if (content != null) {
            res.withChildren(content.render());
        }

        return res;
    }

}
