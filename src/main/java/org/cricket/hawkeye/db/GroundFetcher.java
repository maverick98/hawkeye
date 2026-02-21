/** This file was generated at Fri Feb 20 21:42:21 IST 2026*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) BigBang<->BigCrunch
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
public class GroundFetcher extends HawkPluginModule {

    @Override
    public String getPluginName() {
        return "hawkeye";
    }

    public Grounds getGrounds() {
        return Grounds.getInstance();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return "GroundFetcher";
    }

    @Override
    public boolean startUp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SubTask(name = "sortName", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Ground records sorted ascending by Name. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Ground records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Ground[] sortName(Object... args) {
        Set<Ground> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Ground>) args[0];
        }
        Set<Ground> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getGrounds().getAll();
        }
        Comparator<Ground> cmp = java.util.Comparator.comparing(Ground::getName);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Ground[size]);
    }

    @SubTask(name = "likeName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Ground records where Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Ground records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Ground> likeName(Object... args) {
        Set<Ground> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Ground>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Ground otherGround = new Ground();
        otherGround.setName(name);
        Set<Ground> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getGrounds().getAll();
        }
        Set<Ground> result = new TreeSet<Ground>();
        for (Ground ground : baseSet) {
            if (1 == 1) {
                if (ground.like(otherGround)) {
                    result.add(ground);
                }
            }
        }
        return result;
    }

    @SubTask(name = "countCountry", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Ground records associated with Country. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Ground records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countCountry(Object... args) {
        Set<Ground> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Ground>) args[0];
        }
        Set<Ground> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getGrounds().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "equalName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Ground records where Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Ground records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Ground> equalName(Object... args) {
        Set<Ground> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Ground>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Set<Ground> result = new TreeSet<Ground>();
        Set<Ground> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getGrounds().getAll();
        }
        for (Ground ground : baseSet) {
            if (ground.getName().equals(name)) {
                result.add(ground);
            }
        }
        return result;
    }

    @SubTask(name = "equalCountry", sequence = 1, ignoreException = false, hawkParam = "var innings, var country", definition = "Filters Ground records where Country equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Ground records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Ground> equalCountry(Object... args) {
        Set<Ground> input = null;
        org.cricket.hawkeye.db.Country country = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Ground>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            country = (org.cricket.hawkeye.db.Country) args[1];
        }
        Set<Ground> result = new TreeSet<Ground>();
        Set<Ground> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getGrounds().getAll();
        }
        for (Ground ground : baseSet) {
            if (ground.getCountry().equals(country)) {
                result.add(ground);
            }
        }
        return result;
    }

}
