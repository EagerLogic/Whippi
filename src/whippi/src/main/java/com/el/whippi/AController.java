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
public abstract class AController<$Model> {
    
    private $Model model; 
    private String title = "Untitled";
    
    public final void setModel($Model model) {
        if (model == null) {
            throw new NullPointerException("The model can not be null!");
        }
        this.model = model;
    }
    
    public final $Model getModel() {
        return this.model;
    }
    
    public final void setTitle(String title) {
        if (title == null) {
            title = "Untitled";
        }
        
        this.title = title;
    }
    
    public final String getTitle() {
        return this.title;
    }
    
    public final void init($Model model, String title) {
        this.setModel(model);
        this.setTitle(title);
    }
    
    public abstract ATemplate<$Model, ? extends AController<$Model>> createTemplate();
    
    public abstract $Model index() throws RedirectException;
    
}
