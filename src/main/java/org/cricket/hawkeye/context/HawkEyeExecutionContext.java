/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.context;

import org.cricket.hawkeye.plugin.HawkEyePluginConfiguration;

/**
 *
 * @author manosahu
 */
public class HawkEyeExecutionContext {

    public static enum Type {

        MAIN, PLUGIN;
    }
    private Type type = HawkEyeExecutionContext.Type.MAIN;

    private HawkEyePluginConfiguration configuration;

    private String rootPath = ".";

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
    
    
    public HawkEyePluginConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(HawkEyePluginConfiguration configuration) {
        this.configuration = configuration;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
