/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.sidemenu;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public final class LeafSideMenuItem extends ASideMenuItem {
    
    private static final String NORMAL_BACKGROUND = "rgba(0,0,0, 0.0)";
    private static final String SELECTED_BACKGROUND = "rgb(3, 169, 244)";
    
    private final String href;

    public LeafSideMenuItem(String title, String icon, boolean selected, String href) {
        super(title, icon, selected);
        this.href = href;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; "
                + " background-color: " + (this.isSelected() ? SELECTED_BACKGROUND : NORMAL_BACKGROUND) + ";");
        
        HtmlTag a = new HtmlTag("a");
        a.withAttribute("href", this.href);
        a.withAttribute("style", "padding: 10px 20px 10px 20px; cursor: pointer; display: flex; align-items: center;");
        res.withChild(a);
        
        HtmlTag icon = new HtmlTag("div");
        a.withChild(icon);
        icon.withAttribute("style", "display: inline-block; padding-right: 20px; color: #ddd;");
        icon.withAttribute("class", "material-icons");
        icon.withChild(new HtmlText(this.getIcon()));
        
        HtmlTag title = new HtmlTag("div");
        a.withChild(title);
        title.withAttribute("style", "display: inline-block; size: 18px, font-weight: 600; color: #ddd;");
        title.withChild(new HtmlText(this.getTitle()));
        
        return res;
    }
    
}
