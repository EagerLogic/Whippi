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
public enum EHAlign {

    LEFT("left"),
    CENTER("center"),
    RIGHT("right");

    private final String cssValue;

    private EHAlign(String cssValue) {
        this.cssValue = cssValue;
    }

    public String getCssValue() {
        return cssValue;
    }

}
