/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.sidemenu;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class GroupSideMenuItem extends ASideMenuItem {
    
    private static final String NORMAL_BACKGROUND = "rgba(0,0,0, 0.0)";
    private static final String SELECTED_BACKGROUND = "rgb(3, 169, 244)";
    
    private final List<SubMenuItem> items = new ArrayList<>();

    public GroupSideMenuItem(String title, String icon, boolean selected) {
        super(title, icon, selected);
    }
    
    public GroupSideMenuItem withItem(SubMenuItem item) {
        if (item == null) {
            throw new NullPointerException();
        }
        
        this.items.add(item);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; padding-left: 5px;"
                + " background-color: " + (this.isSelected() ? SELECTED_BACKGROUND : NORMAL_BACKGROUND) + ";");
        
        String firstHref = "";
        if (this.items.size() > 0) {
            firstHref = this.items.get(0).getHref();
        }
        
        HtmlTag mainContainer = new HtmlTag("div");
        mainContainer.withAttribute("style", "width: 100%; ");
        res.withChild(mainContainer);
        
        HtmlTag a = new HtmlTag("a");
        a.withAttribute("href", firstHref);
        a.withAttribute("style", "padding: 10px 20px 10px 0px; cursor: pointer; "
                + "display: flex; align-items: center;");
        mainContainer.withChild(a);
        
        HtmlTag icon = new HtmlTag("div");
        a.withChild(icon);
        icon.withAttribute("style", "display: inline-block; padding-right: 20px; padding-left: 15px; color: #ddd;");
        icon.withAttribute("class", "material-icons");
        icon.withChild(new HtmlText(this.getIcon()));
        
        HtmlTag title = new HtmlTag("div");
        a.withChild(title);
        title.withAttribute("style", "display: inline-block; size: 18px, font-weight: 600; color: #ddd;");
        title.withChild(new HtmlText(this.getTitle()));
        
        if (this.isSelected() && this.items.size() > 0) {
            HtmlTag subContainer = new HtmlTag("div");
            subContainer.withAttribute("style", "padding: 20px 20px 20px 35px; background-color: #303234; width: 100%;");
            res.withChild(subContainer);
            
            for (SubMenuItem item : items) {
                subContainer.withChild(item.render());
            }
        }
        
        return res;
    }
    
}
