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
public enum EFontWeight {
    
    LIGHT(300),
    NORMAL(400),
    SEMI_BOLD(600),
    BOLD(700),
    EXTRA_BOLD(800);
    
    private final int weight;

    private EFontWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
    
}
