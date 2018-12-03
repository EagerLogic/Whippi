/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.htmldom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author david
 */
public final class HtmlTag extends AHtmlElement {
    
    private final String tagName;
    private final Map<String, String> attributes = new HashMap<>();
    private final List<AHtmlElement> children = new ArrayList<>();

    public HtmlTag(String tagName) {
        this.tagName = tagName.trim().toLowerCase();
    }

    public String getTagName() {
        return tagName;
    }
    
    public HtmlTag withAttribute(String name, String value) {
        if (name == null) {
            throw new NullPointerException("The name parameter can not be null!");
        }
        if (value == null) {
            value = "";
        }
        name = name.trim().toLowerCase();
        if (name.length() < 1) {
            throw new IllegalArgumentException("The name can not be empty!");
        }
        
        this.attributes.put(name, value);
        
        return this;
    }
    
    public HtmlTag withChildren(AHtmlElement child) {
        if (child == null) {
            throw new NullPointerException("The child property can not be null!");
        }
        
        this.children.add(child);
        
        return this;
    }

    @Override
    public void render(StringBuilder sb) {
        sb.append("<").append(tagName);
        
        this.renderAttributes(sb);
        
        sb.append(">");
        
        if (this.children.size() > 0) {
            sb.append("\n");
            this.renderChildren(sb);
        }
        
        sb.append("</").append(tagName).append(">\n");
        
    }
    
    private void renderAttributes(StringBuilder sb) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(this.escapeHtmlAttribute(entry.getValue())).append("\"");
        }
    }
    
    private void renderChildren(StringBuilder sb) {
        for (AHtmlElement child : children) {
            child.render(sb);
            sb.append("\n");
        }
    }
    
}
