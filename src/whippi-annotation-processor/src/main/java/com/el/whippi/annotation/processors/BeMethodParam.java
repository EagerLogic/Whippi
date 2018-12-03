/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.annotation.processors;

/**
 *
 * @author david
 */
public class BeMethodParam {
    
    private final String name;
    private final String type;

    public BeMethodParam(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
}
