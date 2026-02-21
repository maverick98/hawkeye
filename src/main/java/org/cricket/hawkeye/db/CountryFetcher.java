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
public class CountryFetcher extends HawkPluginModule {

    @Override
    public String getPluginName() {
        return "hawkeye";
    }

    public Countrys getCountrys() {
        return Countrys.getInstance();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return "CountryFetcher";
    }

    @Override
    public boolean startUp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SubTask(name = "sortName", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Country records sorted ascending by Name. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Country records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Country[] sortName(Object... args) {
        Set<Country> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Country>) args[0];
        }
        Set<Country> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getCountrys().getAll();
        }
        Comparator<Country> cmp = java.util.Comparator.comparing(Country::getName);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Country[size]);
    }

    @SubTask(name = "likeName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Country records where Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Country records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Country> likeName(Object... args) {
        Set<Country> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Country>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Country otherCountry = new Country();
        otherCountry.setName(name);
        Set<Country> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getCountrys().getAll();
        }
        Set<Country> result = new TreeSet<Country>();
        for (Country country : baseSet) {
            if (1 == 1) {
                if (country.like(otherCountry)) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    @SubTask(name = "countGrounds", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Country records associated with Grounds. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Country records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countGrounds(Object... args) {
        Set<Country> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Country>) args[0];
        }
        Set<Country> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getCountrys().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "equalName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Country records where Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Country records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Country> equalName(Object... args) {
        Set<Country> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Country>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        Set<Country> result = new TreeSet<Country>();
        Set<Country> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getCountrys().getAll();
        }
        for (Country country : baseSet) {
            if (country.getName().equals(name)) {
                result.add(country);
            }
        }
        return result;
    }

    @SubTask(name = "equalGrounds", sequence = 1, ignoreException = false, hawkParam = "var innings, var grounds", definition = "Filters Country records where Grounds equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Country records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Country> equalGrounds(Object... args) {
        Set<Country> input = null;
        java.util.HashSet grounds = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Country>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            grounds = (java.util.HashSet) args[1];
        }
        Set<Country> result = new TreeSet<Country>();
        Set<Country> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getCountrys().getAll();
        }
        for (Country country : baseSet) {
            if (country.getGrounds().equals(grounds)) {
                result.add(country);
            }
        }
        return result;
    }

}
