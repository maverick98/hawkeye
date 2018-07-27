/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cricket.hawkeye.dao;

import java.util.Objects;

/**
 *
 * @author reemeeka
 */
public class DownloadTask {
    
    private String downloadURL;

    public DownloadTask(){
        
    }
    public DownloadTask(String url){
        this.downloadURL = url;
    }
    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.downloadURL);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DownloadTask other = (DownloadTask) obj;
        if (!Objects.equals(this.downloadURL, other.downloadURL)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DownloadTask{" + "downloadURL=" + downloadURL + '}';
    }
    
    
    
}
