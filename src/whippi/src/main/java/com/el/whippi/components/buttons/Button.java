/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.buttons;

import com.el.whippi.components.AComponent;
import com.el.whippi.components.EColor;
import com.el.whippi.components.ESize;
import com.el.whippi.feactions.AFeAction;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public final class Button extends AComponent {
    
    private String text = "";
    private EColor color = EColor.PRIMARY;
    private ESize size = ESize.NORMAL;
    private AFeAction onClick;
    private String href = null;
    private boolean inNewTab = false;
    
    public Button() {
        
    }
    
    public Button(String text) {
        this.withText(text);
    }
    
    public Button withText(String text) {
        if (text == null) {
            text = "";
        }
        this.text = text;
        return this;
    }
    
    public Button withSize(ESize size) {
        if (size == null) {
            size = ESize.NORMAL;
        }
        this.size = size;
        return this;
    }
    
    public Button withColor(EColor color) {
        if (color == null) {
            color = EColor.PRIMARY;
        }
        this.color = color;
        return this;
    }
    
    public Button withOnClick(AFeAction onClickAction) {
        this.onClick = onClickAction;
        return this;
    }
    
    public Button withHref(String href) {
        this.href = href;
        
        return this;
    }
    
    public Button withInNewTab(boolean inNewTab) {
        this.inNewTab = inNewTab;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag(this.href == null ? "button" : "a");
        
        String classes = "waves-effect waves-light";
        if (this.size == ESize.SMALL) {
            classes += " btn-small";
        } else if (this.size == ESize.SMALL) {
            classes += " btn-large";
        } else {
            classes += " btn";
        }
        classes += " " + this.color.getStyleClass();
        
        res.withAttribute("class", classes);
        
        if (this.href != null) {
            res.withAttribute("href", this.href);
            if (this.inNewTab) {
                res.withAttribute("target", "_blank");
            }
        }
        
        res.withChild(new HtmlText(text));
        
        if (this.onClick != null) {
            res.withAttribute("onClick", this.onClick.toJavascript());
        }
        
        return res;
    }
    
}
