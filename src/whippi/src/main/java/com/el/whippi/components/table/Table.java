/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.table;

import com.el.whippi.components.AComponent;
import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public final class Table extends AComponent {
    
    private Row headLine;
    private final List<Row> rows = new ArrayList<>();
    
    public Table withHead(Row row) {
        this.headLine = row;
        
        return this;
    }
    
    public Table withRow(Row row) {
        if (row == null) {
            throw new NullPointerException();
        }
        
        this.rows.add(row);
        
        return this;
    }
    

    @Override
    protected AHtmlElement onRender() {
        HtmlTag res = new HtmlTag("table");
        res.withAttribute("class", "table");
        
        if (headLine != null) {
            HtmlTag thead = new HtmlTag("thead");
            res.withChildren(thead);
            
            thead.withChildren(headLine.render(true));
        }
        
        HtmlTag tbody = new HtmlTag("tbody");
        res.withChildren(tbody);
        for (Row row : rows) {
            tbody.withChildren(row.render(false));
        }
        
        return res;
    }
    
}
