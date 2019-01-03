/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.input;

import com.el.whippi.components.AComponent;
import com.el.whippi.feactions.AFeAction;
import com.el.whippi.feactions.FeValue;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;

/**
 *
 * @author david
 */
public class CheckBox extends AComponent {

    private String label;
    private boolean checked = false;
    private String infoMessage;
    private String errorMessage;
    private boolean disabled = false;
    
    private AFeAction onClick;

    private final FeValue<Boolean> feChecked;

    public CheckBox() {
        this.feChecked = new FeValue<>("document.getElementById('" + getId() + "-input').checked");
    }

    public CheckBox withLabel(String label) {
        this.label = label;

        return this;
    }

    public CheckBox withChecked(boolean checked) {
        this.checked = checked;

        return this;
    }

    public CheckBox withInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;

        return this;
    }

    public CheckBox withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

        return this;
    }

    public CheckBox withDisabled(boolean disabled) {
        this.disabled = disabled;

        return this;
    }
    
    public CheckBox withOnClick(AFeAction onClick) {
        this.onClick = onClick;
        
        return this;
    }

    public FeValue<Boolean> getFeChecked() {
        return this.feChecked;
    }

    @Override
    protected AHtmlElement onRender() {
//        <label>
//        <input type="checkbox" class="filled-in" checked="checked" />
//        <span>Filled in</span>
//      </label>
        
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "width: 100%; margin-bottom: 30px;");
        
        HtmlTag cb = new HtmlTag("label");
        res.withChild(cb);

        HtmlTag input = new HtmlTag("input");
        cb.withChild(input);
        input.withAttribute("id", this.getId() + "-input");
        input.withAttribute("type", "checkbox");
        input.withAttribute("class", "filled-in" + (this.errorMessage != null ? " invalid" : ""));
        if (this.checked) {
            input.withAttribute("checked", "true");
        }
        if (this.disabled) {
            input.withAttribute("disabled", "true");
        }
        if (this.onClick != null) {
            input.withAttribute("onclick", this.onClick.toJavascript());
        }

        if (this.label != null) {
            HtmlTag label = new HtmlTag("span");
            cb.withChild(label);
            label.withChild(new HtmlText(this.label));

        }

        if (this.errorMessage != null) {
            HtmlTag msg = new HtmlTag("span");
            msg.withAttribute("class", "helper-text");
            msg.withAttribute("style", "display: block; color: #f44336;");
            msg.withChild(new HtmlText(this.errorMessage));
            res.withChild(msg);
        } else if (this.infoMessage != null) {
            HtmlTag msg = new HtmlTag("span");
            msg.withAttribute("class", "helper-text");
            msg.withAttribute("style", "display: block;");
            msg.withChild(new HtmlText(this.infoMessage));
            res.withChild(msg);
        }

        return res;
    }

}
