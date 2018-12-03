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
public final class Paper extends AComponent {
    
    private final List<AComponent> children = new ArrayList<>();
    
    public Paper withChildren(AComponent child) {
        if (children == null) {
            throw new NullPointerException("The child parameter can not be null!");
        }
        
        children.add(child);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; padding: 20px; background-color: #fff; box-shadow: 1px 1px 5px rgba(0,0,0, 0.5);");
        
        for (AComponent child : children) {
            res.withChildren(child.render());
        }
        
        return res;
    }
    
}
