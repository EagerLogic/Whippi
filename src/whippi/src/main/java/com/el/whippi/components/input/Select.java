/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.input;

import com.el.whippi.components.AComponent;
import com.el.whippi.feactions.FeValue;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class Select extends AComponent {
    
    private String label;
    private String value = "";
    private String infoMessage;
    private String errorMessage;
    private boolean disabled = false;
    private final List<SelectItem> items = new ArrayList<>();
    
    private final FeValue<String> feValue;

    public Select() {
        this.feValue = new FeValue<>("document.getElementById('" + getId() + "-input').value");
    }

    public Select withLabel(String label) {
        this.label = label;

        return this;
    }

    public Select withValue(String value) {
        if (value == null) {
            value = "";
        }
        this.value = value;

        return this;
    }

    public Select withInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;

        return this;
    }

    public Select withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

        return this;
    }
    
    public Select withDisabled(boolean disabled) {
        this.disabled = disabled;
        
        return this;
    }
    
    public Select withItem(SelectItem item) {
        if (item == null) {
            throw new NullPointerException();
        }
        
        this.items.add(item);
        
        return this;
    }
    
    public Select withItems(List<SelectItem> items) {
        if (items == null) {
            throw new NullPointerException();
        }
        
        this.items.addAll(items);
        
        return this;
    }
    
    public FeValue<String> getFeValue() {
        return this.feValue;
    }

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", "input-field");
        res.withAttribute("style", "width: 100%; margin-bottom: 30px;");

        HtmlTag input = new HtmlTag("select");
        res.withChild(input);
        input.withAttribute("id", this.getId() + "-input");
        input.withAttribute("class", this.errorMessage != null ? "invalid" : "");
        if (this.disabled) {
            input.withAttribute("disabled", "true");
        }
        
        for (SelectItem item : this.items) {
            HtmlTag opt = new HtmlTag("option");
            opt.withAttribute("value", item.getId());
            opt.withChild(new HtmlText(item.getTitle()));
            if (item.getId().equals(this.value)) {
                opt.withAttribute("selected", "true");
            }
            input.withChild(opt);
        }

        if (this.label != null) {
            HtmlTag label = new HtmlTag("label");
            res.withChild(label);
            label.withAttribute("for", this.getId() + "-input");
            label.withChild(new HtmlText(this.label));

        }
        
        if (this.errorMessage != null) {
            HtmlTag msg = new HtmlTag("span");
            msg.withAttribute("class", "helper-text");
            msg.withAttribute("style", "color: #f44336;");
            msg.withChild(new HtmlText(this.errorMessage));
            res.withChild(msg);
        } else if (this.infoMessage != null) {
            HtmlTag msg = new HtmlTag("span");
            msg.withAttribute("class", "helper-text");
            msg.withChild(new HtmlText(this.infoMessage));
            res.withChild(msg);
        }

        return res;
    }
    
}
