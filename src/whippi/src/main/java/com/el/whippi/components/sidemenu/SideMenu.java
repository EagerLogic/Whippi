/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.sidemenu;

import com.el.whippi.components.AComponent;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class SideMenu extends AComponent {
    
    private final List<ASideMenuItem> menuItems = new ArrayList<>();
    
    public SideMenu withItem(ASideMenuItem item) {
        if (item == null) {
            throw new NullPointerException();
        }
        
        this.menuItems.add(item);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 300px; background-color: #404244; position: fixed; left: 0px; top: 0px; bottom: 0px; padding-top: 80px;");
        
        if (menuItems.size() > 0) {
            res.withChild(renderDivider());
        }
        
        for (ASideMenuItem item : menuItems) {
            res.withChild(item.render());
            res.withChild(renderDivider());
        }
        
        return res;
    }
    
    private HtmlTag renderDivider() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; height: 1px; background-color: rgba(255,255,255, 0.1);");
        
        return res;
    }
    
}
