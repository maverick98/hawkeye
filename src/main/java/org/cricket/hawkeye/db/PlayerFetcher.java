/**
 * This file was generated at Sat Aug 05 12:52:58 IST 2017*Don't you dare edit
 * this file.You will regret it if you do!!!* This file is part of hawkeye*
 * CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.*
 */package org.cricket.hawkeye.db;

import java.util.Set;
import java.util.TreeSet;
import org.hawk.module.annotation.SubTask;
import java.util.Arrays;
import java.util.Comparator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.hawk.module.plugin.HawkPluginModule;
import org.common.di.ScanMe;

/**
 * ** @author msahu
 */
@ScanMe(true)
public class PlayerFetcher extends HawkPluginModule {

    @Override
    public String getPluginName() {
        return "hawk-eye";
    }

    public Players getPlayers() {
        return Players.getInstance();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return "PlayerFetcher";
    }

    @Override
    public boolean startUp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SubTask(name = "sortName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")
    public Player[] sortName(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Player> input = null;
        input = (Set<Player>) args[0];
        Set<Player> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getPlayers().getAll();
        }
        Player[] tmpArray = tmpSet.toArray(new Player[]{});
        Arrays.sort(tmpArray, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Player thisPlayer = (Player) o1;
                Player thatPlayer = (Player) o2;
                return thisPlayer.getName().compareTo(thatPlayer.getName());
            }
        });
        return tmpArray;
    }

    @SubTask(name = "likeName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")
    public Set<Player> likeName(Object... args) {
        Set<Player> input = null;
        java.lang.String name = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Player>) args[0];
            }
            if (args[1] != null) {
                name = (java.lang.String) args[1];
            }
        } else if (args.length == 1) {
            name = (java.lang.String) args[0];
        }
        Player otherPlayer = new Player();
        otherPlayer.setName(name);
        Set<Player> result = new TreeSet<Player>();
        for (Player player : this.getPlayers().getAll()) {
            if (player.like(otherPlayer)) {
                result.add(player);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Player>();
            }
        }
        return result;
    }

    @SubTask(name = "countInnings", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countInnings(Object... args) {
        Set<Player> input = null;
        if (args[0] != null) {
            input = (Set<Player>) args[0];
        }
        Set<Player> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getPlayers().getAll();
        }
        int count = 0;
        for (Player inning : tmpSet) {
            count++;
        }
        return count;
    }

    @SubTask(name = "equalName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")
    public Set<Player> equalName(Object... args) {
        Set<Player> input = null;
        java.lang.String name = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Player>) args[0];
            }
            if (args[1] != null) {
                name = (java.lang.String) args[1];
            }
        } else if (args.length == 1) {
            name = (java.lang.String) args[0];
        }
        Set<Player> result = new TreeSet<Player>();
        for (Player player : this.getPlayers().getAll()) {
            if (player.getName().equals(name)) {
                result.add(player);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Player>();
            }
        }
        return result;
    }

}
