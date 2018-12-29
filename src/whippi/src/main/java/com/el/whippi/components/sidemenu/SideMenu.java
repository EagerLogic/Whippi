/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.sidemenu;

import com.el.whippi.components.AComponent;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class SideMenu extends AComponent {
    
    private final String logoIcon;
    private final String logoIconColor;
    private final String title;
    private final String titleColor;
    private final List<ASideMenuItem> menuItems = new ArrayList<>();
    private final List<ASideMenuItem> bottomMenuItems = new ArrayList<>();

    public SideMenu(String logoIcon, String logoIconColor, String title, String titleColor) {
        this.logoIcon = logoIcon;
        this.logoIconColor = logoIconColor;
        this.title = title;
        this.titleColor = titleColor;
    }
    
    public SideMenu withItem(ASideMenuItem item) {
        if (item == null) {
            throw new NullPointerException();
        }
        
        this.menuItems.add(item);
        
        return this;
    }
    
    public SideMenu withBottomItem(ASideMenuItem item) {
        if (item == null) {
            throw new NullPointerException();
        }
        
        this.bottomMenuItems.add(item);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 300px; background-color: #2F3C4F; position: fixed; left: 0px; top: 0px; bottom: 0px;");
        
        HtmlTag container = new HtmlTag("div");
        container.withAttribute("style", "display: flex; flex-direction: column; align-items: center; width: 100%; height: 100%;");
        res.withChild(container);
        
        HtmlTag icon = new HtmlTag("div");
        icon.withAttribute("style", "font-size: 96px; padding-top: 40px; color: " + this.logoIconColor + ";");
        icon.withAttribute("class", "material-icons");
        icon.withChild(new HtmlText(this.logoIcon));
        container.withChild(icon);
        
        HtmlTag title = new HtmlTag("div");
        title.withAttribute("style", "display: inline-block; font-size: 20px; font-weight: 600; color: " + this.titleColor + ";");
        title.withChild(new HtmlText(this.title));
        container.withChild(title);
        
        HtmlTag divider = new HtmlTag("div").withAttribute("style", "height: 40px;");
        container.withChild(divider);
        
        for (ASideMenuItem smi : this.menuItems) {
            container.withChild(smi.render());
        }
        
        HtmlTag bContainer = new HtmlTag("div");
        bContainer.withAttribute("style", "display: flex; flex-direction: column; flex-grow: 1; align-items: flex-start; justify-content: flex-end; width: 100%; padding-bottom: 40px;");
        container.withChild(bContainer);
        
        for (ASideMenuItem smi : this.bottomMenuItems) {
            bContainer.withChild(smi.render());
        }
        
        return res;
    }
    
    private HtmlTag renderDivider() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; height: 1px; background-color: rgba(255,255,255, 0.1);");
        
        return res;
    }
    
}
