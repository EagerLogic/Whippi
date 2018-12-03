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
public enum EColor {
    
    PRIMARY("primary"),
    SECONDARY("secondary"),
    INFO("info"),
    SUCCESS("success"),
    WARNING("warning"),
    DANGER("danger");
    
    private final String value;
    
    private EColor(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
}
