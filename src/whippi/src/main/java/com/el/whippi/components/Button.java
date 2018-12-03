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
public final class Button extends AComponent {
    
    private String text = "";
    private EColor color = EColor.PRIMARY;
    private ESize size = ESize.NORMAL;
    private EButtonType type = EButtonType.RAISED;
    private AFeAction onClick;
    
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
    
    public Button withType(EButtonType type) {
        if (type == null) {
            type = EButtonType.RAISED;
        }
        this.type = type;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("button");
        
        String classes = "btn " + this.type.getCssClass();
        classes += " btn-" + this.color.getValue();
        classes += " btn-" + this.size.getValue();
        
        res.withAttribute("class", classes);
        res.withChildren(new HtmlText(text));
        
        if (this.onClick != null) {
            res.withAttribute("onClick", this.onClick.toJavascript());
        }
        
        return res;
    }
    
}
