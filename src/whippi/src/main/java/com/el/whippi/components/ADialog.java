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
    private final List<AComponent> actionButtons = new ArrayList<>();
    private String title;
    private String infoText;
    private String errorMessage;
    private AFeAction fadeClickedAction;

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

    protected ADialog withActionButton(AComponent button) {
        if (button == null) {
            throw new NullPointerException("The button parameter can not be null!");
        }

        this.actionButtons.add(button);

        return this;
    }

    protected ADialog onFadeClickedAction(AFeAction action) {
        this.fadeClickedAction = action;

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
//        <div style={this.createFadeStyle()} onClick={this.onFadeClicked}>
        HtmlTag res = new HtmlTag("div");
        res.withAttribute("style", "position: fixed; background-color: rgba(0,0,0, 0.7); overflow: auto; left: 0px; top: 0px; right: 0px; bottom: 0px; z-index: 10000;");
        if (this.fadeClickedAction != null) {
            res.withAttribute("onclick", this.fadeClickedAction.toJavascript());
        }

//                <div style={{ display: "table", width: "100%", minHeight: "100%" }}>
        HtmlTag table = new HtmlTag("div");
        table.withAttribute("style", "display: table; width: 100%; min-height: 100%;");
        res.withChild(table);

//                    <div style={{ display: "table-row", height: "40px" }}>
        HtmlTag topRow = new HtmlTag("div");
        topRow.withAttribute("style", "display: table-row; height: 40px;");
        table.withChild(topRow);

//                        <div style={{ display: "table-cell" }}>
        topRow.withChild(new HtmlTag("div").withAttribute("style", "display: table-cell;"));

//                    <div style={{ display: "table-row" }}>
        HtmlTag middleRow = new HtmlTag("div");
        middleRow.withAttribute("style", "display: table-row");
        table.withChild(middleRow);

//                        <div style={{ display: "table-cell", textAlign: "center", verticalAlign: "middle" }}>
        HtmlTag middleCell = new HtmlTag("div")
                .withAttribute("style", "display: table-cell; text-align: center; vertical-align: middle;");
        middleRow.withChild(middleCell);

//                            <div style={{ display: "inline-block" }}>
        HtmlTag outerWrapper = new HtmlTag("div").withAttribute("style", "display: inline-block;");
        middleCell.withChild(outerWrapper);

//                                <div style={{ display: 'inline-block' }} onClick={(e) => {e.stopPropagation();}}>
        HtmlTag innerWrapper = new HtmlTag("div").withAttribute("style", "display: inline-block").withAttribute("onclick", "event.stopPropagation();");
        outerWrapper.withChild(innerWrapper);

        HtmlTag dialog = new HtmlTag("div");
        dialog.withAttribute("style", "width: 600px; padding: 20px; text-align: left; background-color: #fff; box-shadow: 1px 1px 10px rgba(0,0,0,0.5);");
        innerWrapper.withChild(dialog);

        if (this.title != null) {
            HtmlTag title = new HtmlTag("div").withAttribute("style", "font-size: 20px; font-weight: 600; color: #000; margin-bottom: 20px;");
            title.withChild(new HtmlText(this.title));
            dialog.withChild(title);
        }
        
        if (this.infoText != null) {
            HtmlTag title = new HtmlTag("div").withAttribute("style", "font-size: 16px; text-align: center; color: #aaa; margin-bottom: 20px;");
            title.withChild(new HtmlText(this.infoText));
            dialog.withChild(title);
        }
        
        if (this.errorMessage != null) {
            HtmlTag title = new HtmlTag("div").withAttribute("style", "font-size: 16px; text-align: center; color: #f44336; margin-bottom: 20px;");
            title.withChild(new HtmlText(this.errorMessage));
            dialog.withChild(title);
        }
        
        for (AComponent child : this.children) {
            dialog.withChild(child.render());
        }
        
        if (this.actionButtons.size() > 0) {
            HtmlTag dialogFooter = new HtmlTag("div");
            dialogFooter.withAttribute("style", "text-align: right; vertical-align: middle; padding-top: 40px;");
            dialog.withChild(dialogFooter);
            
            for (AComponent c : this.actionButtons) {
                dialogFooter.withChild(c.render());
            }
        }

//                    <div style={{ display: "table-row", height: "40px" }}>
        HtmlTag bottomRow = new HtmlTag("div").withAttribute("style", "display: table-row; height: 40px;");
        table.withChild(bottomRow);

//                        <div style={{ display: "table-cell" }}>
        HtmlTag bottomCell = new HtmlTag("div").withAttribute("style", "display: table-cell");
        bottomRow.withChild(bottomCell);

        return res;
    }

}
