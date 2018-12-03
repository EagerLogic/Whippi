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
public final class TextField extends AComponent {

    private final boolean isPassword;
    private String label;
    private String value = "";
    private String infoMessage;
    private String errorMessage;
    
    private final FeValue<String> feValue;

    public TextField() {
        this(false);
    }

    public TextField(boolean isPassword) {
        this.isPassword = isPassword;
        this.feValue = new FeValue<>("document.getElementById('" + getId() + "-input').value");
    }

    public TextField withLabel(String label) {
        this.label = label;

        return this;
    }

    public TextField withValue(String value) {
        if (value == null) {
            value = "";
        }
        this.value = value;

        return this;
    }

    public TextField withInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;

        return this;
    }

    public TextField withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

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
        input.withAttribute("type", isPassword ? "password" : "text");
        input.withAttribute("class", "form-control" + (this.errorMessage != null ? " is-invalid" : ""));
        input.withAttribute("style", "box-shadow: none;");
        input.withAttribute("value", this.value);

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
