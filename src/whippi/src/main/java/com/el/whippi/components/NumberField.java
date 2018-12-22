/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.feactions.FeValue;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class NumberField extends AComponent {

    private String label;
    private String value = "";
    private String infoMessage;
    private String errorMessage;
    private boolean disabled = false;
    
    private final FeValue<String> feValue;

    public NumberField() {
        this.feValue = new FeValue<>("document.getElementById('" + getId() + "-input').value");
    }

    public NumberField withLabel(String label) {
        this.label = label;

        return this;
    }

    public NumberField withValue(String value) {
        if (value == null) {
            value = "";
        }
        this.value = value;

        return this;
    }

    public NumberField withInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;

        return this;
    }

    public NumberField withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

        return this;
    }
    
    public NumberField withDisabled(boolean disabled) {
        this.disabled = disabled;
        
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
        HtmlTag input = new HtmlTag("input");
        res.withChildren(input);
        input.withAttribute("id", this.getId() + "-input");
        input.withAttribute("type", "number");
        input.withAttribute("class", "form-control" + (this.errorMessage != null ? " is-invalid" : ""));
        input.withAttribute("style", "box-shadow: none;");
        input.withAttribute("value", this.value);
        if (this.disabled) {
            input.withAttribute("disabled", "true");
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
