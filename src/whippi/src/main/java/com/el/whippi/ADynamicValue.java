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
public abstract class ADynamicValue<$Type> {
    
    private $Type value;
    
    public abstract String createReadScript();

    public $Type getValue() {
        return value;
    }
    
}
