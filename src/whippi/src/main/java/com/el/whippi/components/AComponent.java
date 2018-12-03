/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.ARenderable;
import com.el.whippi.htmldom.HtmlTag;

/**
 *
 * @author david
 */
public abstract class AComponent extends ARenderable {
    
    private static long nextId = 0;
    
    private static synchronized long getNextId() {
        nextId = nextId + 1;
        return nextId;
    }
    
    private final String id;

    public AComponent() {
        this.id = this.getClass().getSimpleName() + "-" + getNextId();
    }

    public final String getId() {
        return id;
    }
    
    protected final void renderId(AHtmlElement element) {
        if (element instanceof HtmlTag) {
            ((HtmlTag)element).withAttribute("id", this.getId());
        }
    }
    
    public final AHtmlElement render() {
        AHtmlElement res = this.onRender();
        if (res instanceof HtmlTag) {
            ((HtmlTag)res).withAttribute("id", this.getId());
        }
        return res;
    }
    
    protected abstract AHtmlElement onRender();
    
}
