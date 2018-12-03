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
public class ExecJs extends AFeAction {
    
    private final String js;
    
    public ExecJs(String js) {
        if (js == null) {
            throw new NullPointerException("The js parameter can not be null!");
        }
        
        this.js = js;
    }

    @Override
    public String toJavascript() {
        return this.js;
    }
    
}
