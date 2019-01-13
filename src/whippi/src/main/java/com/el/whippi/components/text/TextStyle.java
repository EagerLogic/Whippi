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
public class TextStyle {
    
    private final int size;
    private final EFontWeight weight;
    private final String color;

    public TextStyle(int size, EFontWeight weight, String color) {
        this.size = size;
        this.weight = weight;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public EFontWeight getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }
    
}
