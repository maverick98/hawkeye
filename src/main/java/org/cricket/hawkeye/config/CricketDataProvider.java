/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.config;

import org.hawk.plugin.metadata.Classpath;
import org.hawk.software.Software;

/**
 *
 * @author manosahu
 */
public class CricketDataProvider {

    private Classpath classpath;

    private String cricDataDAO;

  

    private String tabPath;
    
    private Software software;

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    

   

    public Classpath getClasspath() {
        return classpath;
    }

    public void setClasspath(Classpath classpath) {
        this.classpath = classpath;
    }

    public String getCricDataDAO() {
        return cricDataDAO;
    }

    public void setCricDataDAO(String cricDataDAO) {
        this.cricDataDAO = cricDataDAO;
    }

  

    public String getTabPath() {
        return tabPath;
    }

    public void setTabPath(String tabPath) {
        this.tabPath = tabPath;
    }

}
