/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

/**
 *
 * @author david
 */
public class WhippiPostRequest {
    
    private Object model;
    private Object[] params;
    private String methodName;
    private String title;

    public Object getModel() {
        return model;
    }

    public Object[] getParams() {
        return params;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getTitle() {
        return title;
    }
    
}
