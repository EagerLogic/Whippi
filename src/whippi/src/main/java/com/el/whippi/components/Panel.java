/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class Panel extends AComponent {
    
    private EHAlign hAlign = EHAlign.LEFT;
    private final List<AComponent> children = new ArrayList<>();
    private int padding = 0;
    private String width = "100%";
    
    public Panel withHAlign(EHAlign hAlign) {
        if (hAlign == null) {
            throw new NullPointerException();
        }
        
        this.hAlign = hAlign;
        
        return this;
    }
    
    public Panel withChild(AComponent child) {
        if (child == null) {
            throw new NullPointerException();
        }
        
        this.children.add(child);
        
        return this;
    }
    
    public Panel withPadding(int padding) {
        if (padding < 0) {
            padding = 0;
        }
        
        this.padding = padding;
        
        return this;
    }
    
    public Panel withWidth(String width) {
        if (width == null) {
            width = "100%";
        }
        
        this.width = width;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "display: flex; flex-direction: column; width: " + this.width + "; padding-bottom: " + padding + "px; align-items: " + hAlign.getFlexValue() + ";");
        
        for (AComponent child : this.children) {
            res.withChild(child.render());
        }
        
        return res;
    }
    
}
