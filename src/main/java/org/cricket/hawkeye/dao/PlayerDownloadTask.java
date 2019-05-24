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
public class PlayerDownloadTask extends DownloadTask {

    private String countryName;
    private String playerName;

    public PlayerDownloadTask() {

    }

    public PlayerDownloadTask(String playerURL) {
        super(playerURL);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.countryName);
        hash = 29 * hash + Objects.hashCode(this.playerName);
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
        final PlayerDownloadTask other = (PlayerDownloadTask) obj;
        if (!Objects.equals(this.countryName, other.countryName)) {
            return false;
        }
        if (!Objects.equals(this.playerName, other.playerName)) {
            return false;
        }
        return true;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "PlayerDownloadTask{" + "countryName=" + countryName + ", playerName=" + playerName + '}';
    }

}
