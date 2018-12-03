/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components;

/**
 *
 * @author david
 */
public enum EButtonType {
    
    FLAT(""),
    RAISED("btn-raised");
    
    private final String cssClass;

    private EButtonType(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }
    
}
