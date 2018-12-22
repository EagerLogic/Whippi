/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.text;

/**
 *
 * @author david
 */
public enum ETextStyle {
    
    BODY(""),
    H1("h1"),
    H2("h2"),
    H3("h3"),
    H4("h4"),
    H5("h5"),
    H6("h6"),
    DISPLAY1("display-1"),
    DISPLAY2("display-2"),
    DISPLAY3("display-3"),
    DISPLAY4("display-4"),
    LEAD("lead");
    
    private final String cssStyle;
    
    private ETextStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getCssStyle() {
        return cssStyle;
    }
    
}
