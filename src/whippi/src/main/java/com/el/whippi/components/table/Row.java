/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.table;

import com.el.whippi.htmldom.AHtmlElement;
import com.el.whippi.htmldom.HtmlTag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class Row {
    
    private final List<Cell> cells = new ArrayList<>();
    
    public Row withCell(Cell cell) {
        if (cell == null) {
            throw new NullPointerException();
        }
        
        this.cells.add(cell);
        
        return this;
    }
    
    AHtmlElement render(boolean isHead) {
        HtmlTag res = new HtmlTag("tr");
        
        for (Cell cell : cells) {
            res.withChildren(cell.render(isHead ? "col" : null));
        }
        
        return res;
    }
    
}
