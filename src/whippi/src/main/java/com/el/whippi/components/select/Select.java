/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.select;

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
        this.feValue = new FeValue<>("document.getElementById('" + getId() + "-select').value");
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
//        <div class="form-group">
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", "form-group");

        if (this.label != null) {
//            <label for="inputPassword6">Password</label>
            HtmlTag label = new HtmlTag(this.label);
            res.withChildren(label);
            label.withAttribute("for", this.getId() + "-input");
            label.withAttribute("class", "bmd-label-floating");
            label.withChildren(new HtmlText(this.label));
        }

//            <input type="password" id="inputPassword6" class="form-control mx-sm-3" aria-describedby="passwordHelpInline">
        HtmlTag input = new HtmlTag("select");
        res.withChildren(input);
        input.withAttribute("id", this.getId() + "-select");
        input.withAttribute("class", "form-control" + (this.errorMessage != null ? " is-invalid" : ""));
        input.withAttribute("style", "box-shadow: none;");
        if (this.disabled) {
            input.withAttribute("disabled", "true");
        }
        
        for (SelectItem item : items) {
            HtmlTag opt = new HtmlTag("option");
            opt.withAttribute("value", item.getId());
            opt.withChildren(new HtmlText(item.getTitle()));
            if (item.getId().equals(this.value)) {
                opt.withAttribute("selected", "true");
            }
            input.withChildren(opt);
        }

        if (this.errorMessage != null) {
//            <div class="invalid-feedback">
//                Please choose a username.
//            </div>
            HtmlTag error = new HtmlTag("div");
            res.withChildren(error);
            error.withAttribute("class", "invalid-feedback");
            error.withChildren(new HtmlText(this.errorMessage));
        } else if (this.infoMessage != null) {
//            <small id="passwordHelpInline" class="text-muted">
//                Must be 8-20 characters long.
//            </small>
            HtmlTag small = new HtmlTag("span");
            res.withChildren(small);
            small.withAttribute("class", "bmd-help");
            small.withChildren(new HtmlText(this.infoMessage));
        }
//        </div>

        return res;
    }
    
}
