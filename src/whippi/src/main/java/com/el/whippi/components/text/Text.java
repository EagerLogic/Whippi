/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.text;

import com.el.whippi.components.AComponent;
import com.el.whippi.components.EHAlign;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class Text extends AComponent {

    private static final TextStyle DEFAULT_STYLE = new TextStyle(16, EFontWeight.NORMAL, "#222");

    private TextStyle textStyle = DEFAULT_STYLE;
    private EHAlign hAlign = EHAlign.LEFT;
    private String text;
    private Integer maxWidth;
    private String width;

    public String getText() {
        return text;
    }

    public Text withStyle(TextStyle style) {
        if (style == null) {
            style = DEFAULT_STYLE;
        }

        this.textStyle = style;

        return this;
    }

    public Text withHAlign(EHAlign hAlign) {
        if (hAlign == null) {
            hAlign = EHAlign.LEFT;
        }

        this.hAlign = hAlign;

        return this;
    }

    public Text withText(String text) {
        this.text = text;

        return this;
    }
    
    public Text withMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
        
        return this;
    }
    
    public Text withWidth(String width) {
        this.width = width;
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        
        String style = (width == null ? "" : "width: " + width + "; ")
                + "font-size: " + textStyle.getSize() + "px; "
                + "font-weight: " + textStyle.getWeight().getWeight() + "; "
                + "color: " + textStyle.getColor() + "; "
                + "text-align: " + hAlign.getCssValue() + ";";
        if (maxWidth != null) {
            style += "max-width: " + maxWidth + "px; ";
        }
        
        res.withAttribute("style", style);

        if (text != null) {
            res.withChild(new HtmlText(text));
        }

        return res;
    }

}
