/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.components.sidemenu.SideMenu;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.BlankHtmlTag;
import com.el.whippi.htmldom.HtmlTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class DefaultPageLayout extends APageLayout {
    
    private SideMenu sideMenu;
    private final List<AComponent> content = new ArrayList<>();
    
    private int hPadding = 80;
    private int vPadding = 40;
    private String bgColor = null;
    
    public DefaultPageLayout withSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
        
        return this;
    }
    
    public DefaultPageLayout withContent(AComponent content) {
        if (content == null) {
            throw new NullPointerException();
        }
        
        this.content.add(content);
        
        return this;
    }
    
    public DefaultPageLayout withHPadding(int hPadding) {
        if (hPadding < 0) {
            hPadding = 0;
        }
        
        this.hPadding = hPadding;
        
        return this;
    }
    
    public DefaultPageLayout withVPadding(int vPadding) {
        if (vPadding < 0) {
            vPadding = 0;
        }
        
        this.vPadding = vPadding;
        
        return this;
    }
    
    public DefaultPageLayout withBgColor(String color) {
        this.bgColor = color;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        BlankHtmlTag res = new BlankHtmlTag();
        
        if (sideMenu != null) {
            res.withChildren(this.sideMenu.render());
        }
        
        int paddingLeft = hPadding;
        if (this.sideMenu != null) {
            paddingLeft = hPadding + 300;
        }
        
        String bg = bgColor;
        if (bg == null) {
            bg = "#f4f4f4";
        }
        
        HtmlTag container = new HtmlTag("div");
        container.withAttribute("style", "padding: " + vPadding + "px " + hPadding + "px " + vPadding + "px " + paddingLeft + "px; width: 100%; min-width: 1280px; min-height: 100%; background-color: " + bg + ";");
        res.withChildren(container);
        
        for (AComponent child : content) {
            container.withChild(child.render());
        }
        
        return res;
    }
    
}
