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
    private boolean disabled = false;

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

    public TextField withDisabled(boolean disabled) {
        this.disabled = disabled;

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

        HtmlTag input = new HtmlTag("input");
        res.withChild(input);
        input.withAttribute("id", this.getId() + "-input");
        input.withAttribute("type", isPassword ? "password" : "text");
        input.withAttribute("class", this.errorMessage != null ? "invalid" : "");
        input.withAttribute("value", this.value);
        if (this.disabled) {
            input.withAttribute("disabled", "true");
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
