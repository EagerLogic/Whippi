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

/**
 *
 * @author david
 */
public final class SubMenuItem extends AComponent {
    
    private final String title;
    private final String href;
    private final boolean selected;

    public SubMenuItem(String title, String href, boolean selected) {
        this.title = title;
        this.href = href;
        this.selected = selected;
    }
    
    public String getHref() {
        return this.href;
    }
    
    // trip_origin
    

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; padding-top: 5px; padding-bottom: 5px;");
        
        HtmlTag a = new HtmlTag("a");
        a.withAttribute("href", this.href);
        a.withAttribute("style", "display: flex; align-items: center; width: 100%;");
        res.withChild(a);
        
        HtmlTag icon = new HtmlTag("div");
        icon.withAttribute("style", "display: inline-block; font-size: 10px; color: " + (this.selected ? "rgb(3, 169, 244)" : "rgba(255, 255, 255, 0.4)") + ";");
        icon.withAttribute("class", "material-icons");
        icon.withChild(new HtmlText("trip_origin"));
        a.withChild(icon);
        
        HtmlTag title = new HtmlTag("div");
        title.withAttribute("style", "display: inline-block; padding-left: 10px; font-size: 16px; color: " + (this.selected ? "rgb(3, 169, 244)" : "rgba(255, 255, 255, 0.75)") + ";");
        title.withChild(new HtmlText(this.title));
        a.withChild(title);
        
        return res;
    }
    
}
