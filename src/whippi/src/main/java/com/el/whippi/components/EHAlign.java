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

    LEFT("left", "flex-start"),
    CENTER("center", "center"),
    RIGHT("right", "flex-end");

    private final String cssValue;
    private final String flexValue;

    private EHAlign(String cssValue, String flexValue) {
        this.cssValue = cssValue;
        this.flexValue = flexValue;
    }

    public String getCssValue() {
        return cssValue;
    }

    public String getFlexValue() {
        return flexValue;
    }

}
