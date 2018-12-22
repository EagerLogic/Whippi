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
public interface IAuthenticationProvider<$User> {
    
    public $User authenticate(HttpServletRequest request) throws RedirectException;
    
}
