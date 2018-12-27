/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.buttons;

import com.el.whippi.components.AComponent;
import com.el.whippi.components.EColor;
import com.el.whippi.feactions.AFeAction;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class FloatingButton extends AComponent {
    
    private final String icon;
    private EColor color = EColor.DANGER;
    private AFeAction onClick;

    public FloatingButton(String icon) {
        this.icon = icon;
    }
    
    public FloatingButton withColor(EColor color) {
        if (color == null) {
            throw new NullPointerException();
        }
        
        this.color = color;
        
        return this;
    }
    
    public FloatingButton withOnClick(AFeAction action) {
        this.onClick = action;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        // <div class="fixed-action-btn">
        // <a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", "fixed-action-btn");
        
        HtmlTag a = new HtmlTag("a");
        res.withChild(a);
        String classes = "btn-floating btn-large waves-effect waves-light " + this.color.getStyleClass();
        a.withAttribute("class", classes);
        
        if (this.onClick != null) {
            a.withAttribute("onclick", this.onClick.toJavascript());
        }
        
        HtmlTag i = new HtmlTag("i");
        i.withAttribute("class", "material-icons");
        i.withChild(new HtmlText(icon));
        a.withChild(i);
        
        return res;
    }
    
}
