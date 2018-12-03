/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.feactions;

import java.util.List;

/**
 *
 * @author david
 */
public class CallBeMethod extends AFeAction {
    
    private final String methodName;
    private final List<AFeValue<?>> values;

    public CallBeMethod(String methodName, List<AFeValue<?>> values) {
        if (methodName == null) {
            throw new NullPointerException();
        }
        if (values == null) {
            throw new NullPointerException();
        }
        this.methodName = methodName;
        this.values = values;
    }

    @Override
    public String toJavascript() {
        StringBuilder sb = new StringBuilder();
        sb.append("Whippi.callBackendMethod('" + methodName + "', [");
        
        boolean isFirst = true;
        for (AFeValue<?> value : values) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append(value.toJavascript());
        }
        
        sb.append("]);");
        
        return sb.toString();
    }

    public String getMethodName() {
        return methodName;
    }

    public List<AFeValue<?>> getValues() {
        return values;
    }
    
}
