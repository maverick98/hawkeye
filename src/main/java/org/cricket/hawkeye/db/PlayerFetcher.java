/** This file was generated at Fri Feb 20 21:42:20 IST 2026*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) BigBang<->BigCrunch
 * Manoranjan Sahu, All Rights are left.*
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
        return "hawkeye";
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

    @SubTask(name = "sortName", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Player records sorted ascending by Name. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Player records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Player[] sortName(Object... args) {
        Set<Player> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Player>) args[0];
        }
        Set<Player> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getPlayers().getAll();
        }
        Comparator<Player> cmp = java.util.Comparator.comparing(Player::getName);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Player[size]);
    }

    @SubTask(name = "likeName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Player records where Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Player records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Player> likeName(Object... args) {
        Set<Player> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Player>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Player otherPlayer = new Player();
        otherPlayer.setName(name);
        Set<Player> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getPlayers().getAll();
        }
        Set<Player> result = new TreeSet<Player>();
        for (Player player : baseSet) {
            if (1 == 1) {
                if (player.like(otherPlayer)) {
                    result.add(player);
                }
            }
        }
        return result;
    }

    @SubTask(name = "countInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Player records associated with Innings. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Player records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countInnings(Object... args) {
        Set<Player> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Player>) args[0];
        }
        Set<Player> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getPlayers().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "equalName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Player records where Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Player records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Player> equalName(Object... args) {
        Set<Player> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Player>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Set<Player> result = new TreeSet<Player>();
        Set<Player> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getPlayers().getAll();
        }
        for (Player player : baseSet) {
            if (player.getName().equals(name)) {
                result.add(player);
            }
        }
        return result;
    }

}
