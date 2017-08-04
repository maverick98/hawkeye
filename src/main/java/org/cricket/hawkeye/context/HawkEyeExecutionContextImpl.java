/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.context;

/**
 *
 * @author manosahu
 */

public class HawkEyeExecutionContextImpl implements IHawkEyeExecutionContext{
    
    private HawkEyeExecutionContext hawkExecutionContext;

    public HawkEyeExecutionContext getHawkExecutionContext() {
        return hawkExecutionContext;
    }

    public void setHawkExecutionContext(HawkEyeExecutionContext hawkExecutionContext) {
        this.hawkExecutionContext = hawkExecutionContext;
    }
    
    
    @Override
    public void setExecutionContext(HawkEyeExecutionContext hawkEyeExecutionContext) {
        this.hawkExecutionContext = hawkEyeExecutionContext;
    }

    @Override
    public HawkEyeExecutionContext getExecutionContext() {
        return this.hawkExecutionContext;
    }
    
}
