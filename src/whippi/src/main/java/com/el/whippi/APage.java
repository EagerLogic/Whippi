/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public abstract class APage {
    
    public abstract boolean match(String url, HttpServletRequest req);
    
    public abstract AController<?> createController(HttpServletRequest req);
    
}
