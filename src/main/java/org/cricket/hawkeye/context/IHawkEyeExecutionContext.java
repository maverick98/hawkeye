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
public interface IHawkEyeExecutionContext {
    
    public void setExecutionContext(HawkEyeExecutionContext hawkEyeExecutionContext);
    
    public HawkEyeExecutionContext getExecutionContext();
}
