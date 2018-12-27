/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.text;

import com.el.whippi.components.AComponent;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class Text extends AComponent {
    
    private ETextStyle textStyle = ETextStyle.BODY;
    private String text;
    
    public Text withStyle(ETextStyle style) {
        if (style == null) {
            throw new NullPointerException();
        }
        
        this.textStyle = style;
        
        return this;
    }
    
    public Text withText(String text) {
        this.text = text;
        
        return this;
    }
    

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", textStyle.getCssStyle());
        res.withAttribute("style", "display: inline-block");
        
        if (text != null) {
            res.withChild(new HtmlText(text));
        }
        
        return res;
    }
    
}
