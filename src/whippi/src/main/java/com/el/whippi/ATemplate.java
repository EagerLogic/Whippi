/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi;

import com.el.whippi.components.APageLayout;

/**
 *
 * @author david
 */
public abstract class ATemplate<$Model, $Controller extends AController<$Model>> {
    
    public abstract APageLayout render($Model model);
    
}
