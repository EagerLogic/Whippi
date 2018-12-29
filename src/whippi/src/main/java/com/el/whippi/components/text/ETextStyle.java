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
    
    BODY("p"),
    H1("h1"),
    H2("h2"),
    H3("h3"),
    H4("h4"),
    H5("h5"),
    H6("h6");
    
    private final String tag;
    
    private ETextStyle(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
    
}
