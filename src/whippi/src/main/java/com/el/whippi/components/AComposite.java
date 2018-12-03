/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.BlankHtmlTag;
import java.util.List;

/**
 *
 * @author david
 */
public abstract class AComposite extends AComponent {
    
    protected abstract List<AComponent> renderComposite();
    
    @Override
    protected final AHtmlElement onRender() {
        List<AComponent> components = this.renderComposite();
        
        BlankHtmlTag res = new BlankHtmlTag();
        if (components == null) {
            return res;
        }
        
        for (AComponent child : components) {
            res.withChildren(child.render());
        }
        
        return res;
    }
    
}
