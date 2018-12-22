/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

import com.el.whippi.feactions.AFeAction;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import com.el.whippi.htmldom.HtmlText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public abstract class ADialog extends AComponent {

    private final List<AComponent> children = new ArrayList<>();
    private final List<Button> actionButtons = new ArrayList<>();
    private String title;
    private String infoText;
    private String errorMessage;
    private boolean closeButtonVisible = false;
    private AFeAction closeButtonClickedAction;

    public ADialog(String title) {
        this.title = title;
    }

    protected ADialog withChild(AComponent child) {
        if (child == null) {
            throw new NullPointerException("The child parameter can not be null!");
        }

        this.children.add(child);

        return this;
    }

    protected ADialog withActionButton(Button button) {
        if (button == null) {
            throw new NullPointerException("The button parameter can not be null!");
        }

        this.actionButtons.add(button);

        return this;
    }

    protected ADialog withCloseButton(boolean closeButtonVisible) {
        this.closeButtonVisible = closeButtonVisible;

        return this;
    }

    protected ADialog onCloseButtonClickedAction(AFeAction action) {
        this.closeButtonClickedAction = action;

        return this;
    }

    protected ADialog withInfoText(String infoText) {
        this.infoText = infoText;

        return this;
    }

    protected ADialog withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;

        return this;
    }

    @Override
    protected final AHtmlElement onRender() {
//        <div class="modal" tabindex="-1" role="dialog">
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("class", "modal");
        res.withAttribute("tabindex", "-1");
        res.withAttribute("role", "dialog");
        res.withAttribute("style", "display: block;");

//            <div class="modal-dialog" role="document">
        HtmlTag dialog = new HtmlTag("div");
        res.withChildren(dialog);
        dialog.withAttribute("class", "modal-dialog");
        dialog.withAttribute("role", "document");

//              <div class="modal-content">
        HtmlTag content = new HtmlTag("div");
        dialog.withChildren(content);
        content.withAttribute("class", "modal-content");

//                <div class="modal-header">
        HtmlTag header = new HtmlTag("div");
        content.withChildren(header);
        header.withAttribute("class", "modal-header");

//                  <h5 class="modal-title">Modal title</h5>
        HtmlTag title = new HtmlTag("h5");
        header.withChildren(title);
        title.withAttribute("class", "modal-title");
        title.withChildren(new HtmlText(this.title));

//                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        if (this.closeButtonVisible) {
            HtmlTag closeButton = new HtmlTag("button");
            header.withChildren(closeButton);
            closeButton.withAttribute("type", "button");
            closeButton.withAttribute("class", "close");
            closeButton.withAttribute("data-dismiss", "modal");
            closeButton.withAttribute("aria-label", "Close");
            
            if (this.closeButtonClickedAction != null) {
                closeButton.withAttribute("onClick", this.closeButtonClickedAction.toJavascript());
            }

//                    <span aria-hidden="true">&times;</span>
            HtmlTag closeIcon = new HtmlTag("span");
            closeButton.withChildren(closeIcon);
            closeIcon.withAttribute("aria-hidden", "true");
            closeIcon.withChildren(new HtmlText("&times;"));
//                  </button>
        }
//                </div>

//                <div class="modal-body">
        HtmlTag body = new HtmlTag("div");
        content.withChildren(body);
        body.withAttribute("class", "modal-body");

        if (this.infoText != null) {
            HtmlTag info = new HtmlTag("div");
            info.withAttribute("style", "width: 100%; padding-bottom: 20px; font-size: 14px; color: #888; text-align: center;");
            info.withChildren(new HtmlText(this.infoText));
            body.withChildren(info);
        }

        if (this.errorMessage != null) {
            HtmlTag error = new HtmlTag("div");
            error.withAttribute("style", "width: 100%; padding-bottom: 20px; font-size: 14px; color: #c00; text-align: center;");
            error.withChildren(new HtmlText(this.errorMessage));
            body.withChildren(error);
        }

        for (AComponent child : children) {
            body.withChildren(child.render());
        }
//                </div>

//                <div class="modal-footer">
        HtmlTag footer = new HtmlTag("div");
        content.withChildren(footer);
        footer.withAttribute("class", "modal-footer");

        for (Button button : actionButtons) {
            footer.withChildren(button.render());
        }
//                </div>
//              </div>
//            </div>
//          </div>

        return res;
    }

}
