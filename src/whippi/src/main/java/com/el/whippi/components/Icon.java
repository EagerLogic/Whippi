/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.feactions.AFeAction;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class Icon extends AComponent {
    
    private final String icon;
    private int size = 20;
    private AFeAction onClick;
    private String color = "#000";
    private String tooltip;

    public Icon(String icon) {
        if (icon == null) {
            throw new NullPointerException();
        }
        this.icon = icon;
    }
    
    public Icon withSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("The size can not be smaller than 1 px!");
        }
        
        this.size = size;
        
        return this;
    }
    
    public Icon withOnClick(AFeAction action) {
        this.onClick = action;
        
        return this;
    }
    
    public Icon withColor(String color) {
        if (color == null) {
            color = "#000";
        }
        
        this.color = color;
        
        return this;
    }
    
    public Icon withTooltip(String tooltip) {
        this.tooltip = tooltip;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", "material-icons");
        String style = "display: inline-block;";
        style += " padding: 3px;";
        style += " color: " + this.color + ";";
        style += " font-size: " + this.size + "px;";
        if (this.onClick != null) {
            style += " cursor: pointer;";
        }
        res.withAttribute("style", style);
        
        if (this.tooltip != null) {
            res.withAttribute("title", this.tooltip);
        }
        
        if (this.onClick != null) {
            res.withAttribute("onclick", this.onClick.toJavascript());
        }
        
        res.withChild(new HtmlText(this.icon));
        
        return res;
    }
    
}
