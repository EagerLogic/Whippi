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

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; padding-bottom: 20px; text-align: " + hAlign.getCssValue() + ";");
        
        for (AComponent child : this.children) {
            res.withChildren(child.render());
        }
        
        return res;
    }
    
}
