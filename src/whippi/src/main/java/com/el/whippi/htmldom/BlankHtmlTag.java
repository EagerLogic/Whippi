/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.htmldom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class BlankHtmlTag extends AHtmlElement {
    
    private final List<AHtmlElement> children = new ArrayList<>();
    
    public BlankHtmlTag withChildren(AHtmlElement child) {
        if (child == null) {
            throw new NullPointerException("The child property can not be null!");
        }
        
        this.children.add(child);
        
        return this;
    }

    @Override
    public void render(StringBuilder sb) {
        for (AHtmlElement child : children) {
            child.render(sb);
            sb.append("\n");
        }
    }
    
}
