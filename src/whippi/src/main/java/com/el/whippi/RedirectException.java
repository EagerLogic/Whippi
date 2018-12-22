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
public class RedirectException extends Exception {
    
    private final String url;

    public RedirectException(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    
}
