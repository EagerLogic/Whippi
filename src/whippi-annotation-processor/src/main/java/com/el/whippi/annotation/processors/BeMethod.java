/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.annotation.processors;

import java.util.List;

/**
 *
 * @author david
 */
public class BeMethod {
    
    private final String name;
    private final List<BeMethodParam> params;

    public BeMethod(String name, List<BeMethodParam> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public List<BeMethodParam> getParams() {
        return params;
    }
    
}
