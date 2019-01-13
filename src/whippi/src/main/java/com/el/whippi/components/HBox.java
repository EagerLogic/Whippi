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
public class HBox extends AComponent {
    
    private final List<AComponent> children = new ArrayList<>();
    
    public HBox withChild(AComponent child) {
        if (child == null) {
            throw new NullPointerException();
        }
        
        children.add(child);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "display: flex; width: 100%; align-items: center;");
        
        for (AComponent child : children) {
            res.withChild(child.render());
        }
        
        return res;
    }
    
}
