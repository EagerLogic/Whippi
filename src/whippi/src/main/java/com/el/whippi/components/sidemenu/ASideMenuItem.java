/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.sidemenu;

import com.el.whippi.components.AComponent;

/**
 *
 * @author david
 */
public abstract class ASideMenuItem extends AComponent {
    
    private final String title;
    private final String icon;
    private final boolean selected;

    public ASideMenuItem(String title, String icon, boolean selected) {
        this.title = title;
        this.icon = icon;
        this.selected = selected;
    }

    protected final String getTitle() {
        return title;
    }

    protected final String getIcon() {
        return icon;
    }

    protected final boolean isSelected() {
        return selected;
    }
    
}
