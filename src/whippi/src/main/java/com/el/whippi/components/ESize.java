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
public enum ESize {
    
    SMALL("sm"),
    NORMAL("md"),
    LARGE("lg");
    
    private final String value;
    
    private ESize(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
}
