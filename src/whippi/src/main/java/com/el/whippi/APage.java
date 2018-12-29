/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
public abstract class APage {
    
    private String header;
    
    public abstract boolean match(String url, HttpServletRequest req);
    
    public abstract AController<?> createController(HttpServletRequest req, HttpServletResponse resp);
    
    public void setHeader(String header) {
        this.header = header;
    }
    
    public String getHeader() {
        return this.header;
    }
    
    
    
}
