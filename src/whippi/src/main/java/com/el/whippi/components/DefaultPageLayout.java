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

    @Override
    protected AHtmlElement onRender() {
        BlankHtmlTag res = new BlankHtmlTag();
        
        if (sideMenu != null) {
            res.withChildren(this.sideMenu.render());
        }
        
        int paddingLeft = 80;
        if (this.sideMenu != null) {
            paddingLeft = 380;
        }
        
        HtmlTag container = new HtmlTag("div");
        container.withAttribute("style", "padding: 40px 80px 40px " + paddingLeft + "px; width: 100%; min-height: 100%; background-color: #f4f4f4;");
        res.withChildren(container);
        
        for (AComponent child : content) {
            container.withChild(child.render());
        }
        
        return res;
    }
    
}
