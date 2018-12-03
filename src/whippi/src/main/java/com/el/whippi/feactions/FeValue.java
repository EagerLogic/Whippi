/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.feactions;

/**
 *
 * @author david
 */
public class FeValue<$Type> extends AFeValue<$Type> {
    
    private final String js;

    public FeValue(String js) {
        if (js == null) {
            throw new NullPointerException();
        }
        this.js = js;
    }

    @Override
    public String toJavascript() {
        return js;
    }
    
}
