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
    
    PRIMARY("teal lighten-1", "#26a69a"),
    SECONDARY("pink lighten-1", "#ec407a"),
    INFO("light-blue lighten-1", "#29b6f6"),
    SUCCESS("light-green darken-1", "#7cb342"),
    WARNING("orange darken-3", "#ef6c00"),
    DANGER("red darken-3", "#c62828"),
    BLACK("grey darken-4", "#212121"),
    GRAY("grey lighten-1", "#bdbdbd"),
    WHITE("white", "#ffffff");
    
    private final String styleClass;
    private final String color;
    
    private EColor(String styleClass, String color) {
        this.styleClass = styleClass;
        this.color = color;
    }
    
    public String getStyleClass() {
        return this.styleClass;
    }

    public String getColor() {
        return color;
    }
    
}
