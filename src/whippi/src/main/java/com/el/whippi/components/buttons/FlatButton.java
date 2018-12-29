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
public class FlatButton extends AComponent {
    
    private String text = "";
    private EColor color;
    private AFeAction onClick;
    
    public FlatButton() {
        
    }
    
    public FlatButton(String text) {
        this.withText(text);
    }
    
    public FlatButton withText(String text) {
        if (text == null) {
            text = "";
        }
        this.text = text;
        return this;
    }
    
    public FlatButton withColor(EColor color) {
        this.color = color;
        
        return this;
    }
    
    public FlatButton withOnClick(AFeAction onClickAction) {
        this.onClick = onClickAction;
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("button");
        
        res.withAttribute("class", "waves-effect waves-teal btn-flat");
        if (this.color != null) {
            res.withAttribute("style", "color: " + this.color.getColor() + ";");
        }
        res.withChild(new HtmlText(text));
        
        if (this.onClick != null) {
            res.withAttribute("onClick", this.onClick.toJavascript());
        }
        
        return res;
    }
    
}
