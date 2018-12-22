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
public abstract class AAuthenticatedController<$User, $Model> extends AController<$Model> {
    
    private $User currentUser;
    
    protected final $User getCurrentUser() {
        return this.currentUser;
    }
    
    protected abstract $User authenticate(HttpServletRequest req, HttpServletResponse resp) throws RedirectException;
    
    boolean doAuthenticate(HttpServletRequest req, HttpServletResponse resp) throws RedirectException {
        $User u = this.authenticate(req, resp);
        if (u == null) {
            return false;
        }
        
        this.currentUser = u;
        
        return true;
    }
    
}
