/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class Paper extends AComponent {
    
    private String title;
    private String infoText;
    private final List<AComponent> children = new ArrayList<>();
    
    public Paper withTitle(String title) {
        this.title = title;
        
        return this;
    }
    
    public Paper withInfoText(String infoText) {
        this.infoText = infoText;
        
        return this;
    }
    
    public Paper withChild(AComponent child) {
        if (children == null) {
            throw new NullPointerException("The child parameter can not be null!");
        }
        
        children.add(child);
        
        return this;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; padding: 20px; background-color: #fff; box-shadow: 1px 1px 10px rgba(0,0,0, 0.3); margin-bottom: 40px;");
        
        if (this.title != null) {
            res.withChild(new HtmlTag("div")
                    .withAttribute("style", "width: 100%; margin-bottom: 40px; font-size: 20px; color: #000; font-weight: 600;")
                    .withChild(new HtmlText(this.title))
            );
        }
        
        if (this.infoText != null) {
            res.withChild(new HtmlTag("div")
                    .withAttribute("style", "width: 100%; margin-bottom: 20px; font-size: 16px; color: #aaa; text-align: center;")
                    .withChild(new HtmlText(infoText))
            );
        }
        
        for (AComponent child : children) {
            res.withChild(child.render());
        }
        
        return res;
    }
    
}
