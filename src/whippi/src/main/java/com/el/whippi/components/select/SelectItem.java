/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.select;

/**
 *
 * @author david
 */

public final class SelectItem {
    
    private final String id;
    private final String title;

    private SelectItem() {
        this(null, null);
    }

    public SelectItem(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
}
