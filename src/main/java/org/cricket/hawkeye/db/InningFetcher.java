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
public class InningFetcher extends HawkPluginModule {

    @Override
    public String getPluginName() {
        return "hawkeye";
    }

    public Innings getInnings() {
        return Innings.getInstance();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return "InningFetcher";
    }

    @Override
    public boolean startUp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SubTask(name = "afterStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings, var startDate", definition = "Filters Inning records where StartDate occurs strictly after the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating the input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> afterStartDate(Object... args) {
        Set<Inning> input = null;
        java.util.Date startDate = null;
        if (args == null || args.length == 0) {
            return new TreeSet<Inning>();
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                startDate = (java.util.Date) args[1];
            }
        } else if (args.length == 1) {
            startDate = (java.util.Date) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStartDate().after(startDate)) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "lessThanPlayerName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly less than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Player wrapper for nested comparison and delegates to lessThanPlayer. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> lessThanPlayerName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Player player = new org.cricket.hawkeye.db.Player();
        player.setName(name);
        return lessThanPlayer(input, player);
    }

    @SubTask(name = "lessThanOppositionName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly less than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Country wrapper for nested comparison and delegates to lessThanOpposition. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> lessThanOppositionName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Country opposition = new org.cricket.hawkeye.db.Country();
        opposition.setName(name);
        return lessThanOpposition(input, opposition);
    }

    @SubTask(name = "lessThanGroundName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly less than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Ground wrapper for nested comparison and delegates to lessThanGround. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> lessThanGroundName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Ground ground = new org.cricket.hawkeye.db.Ground();
        ground.setName(name);
        return lessThanGround(input, ground);
    }

    @SubTask(name = "moreThanPlayer", sequence = 1, ignoreException = false, hawkParam = "var innings, var player", definition = "Filters Inning records where Player is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanPlayer(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Player player = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                player = (org.cricket.hawkeye.db.Player) args[1];
            }
        } else if (args.length == 1) {
            player = (org.cricket.hawkeye.db.Player) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getPlayer().compareTo(player) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanRuns", sequence = 1, ignoreException = false, hawkParam = "var innings, var runs", definition = "Filters Inning records where Runs is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanRuns(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer runs = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                runs = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            runs = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getRuns().compareTo(runs) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanMins", sequence = 1, ignoreException = false, hawkParam = "var innings, var mins", definition = "Filters Inning records where Mins is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanMins(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer mins = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                mins = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            mins = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getMins().compareTo(mins) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings, var ballFaced", definition = "Filters Inning records where BallFaced is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanBallFaced(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer ballFaced = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                ballFaced = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            ballFaced = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getBallFaced().compareTo(ballFaced) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanFours", sequence = 1, ignoreException = false, hawkParam = "var innings, var fours", definition = "Filters Inning records where Fours is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanFours(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer fours = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                fours = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            fours = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getFours().compareTo(fours) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanSixes", sequence = 1, ignoreException = false, hawkParam = "var innings, var sixes", definition = "Filters Inning records where Sixes is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanSixes(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer sixes = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                sixes = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            sixes = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getSixes().compareTo(sixes) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings, var strikeRate", definition = "Filters Inning records where StrikeRate is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanStrikeRate(Object... args) {
        Set<Inning> input = null;
        java.lang.Double strikeRate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                strikeRate = (java.lang.Double) args[1];
            }
        } else if (args.length == 1) {
            strikeRate = (java.lang.Double) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStrikeRate().compareTo(strikeRate) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanPositions", sequence = 1, ignoreException = false, hawkParam = "var innings, var positions", definition = "Filters Inning records where Positions is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanPositions(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer positions = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                positions = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            positions = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getPositions().compareTo(positions) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanInnings", sequence = 1, ignoreException = false, hawkParam = "var innings, var innings", definition = "Filters Inning records where Innings is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanInnings(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer innings = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                innings = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            innings = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getInnings().compareTo(innings) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings, var opposition", definition = "Filters Inning records where Opposition is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanOpposition(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Country opposition = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                opposition = (org.cricket.hawkeye.db.Country) args[1];
            }
        } else if (args.length == 1) {
            opposition = (org.cricket.hawkeye.db.Country) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getOpposition().compareTo(opposition) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanGround", sequence = 1, ignoreException = false, hawkParam = "var innings, var ground", definition = "Filters Inning records where Ground is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanGround(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Ground ground = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                ground = (org.cricket.hawkeye.db.Ground) args[1];
            }
        } else if (args.length == 1) {
            ground = (org.cricket.hawkeye.db.Ground) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getGround().compareTo(ground) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings, var startDate", definition = "Filters Inning records where StartDate is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanStartDate(Object... args) {
        Set<Inning> input = null;
        java.util.Date startDate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                startDate = (java.util.Date) args[1];
            }
        } else if (args.length == 1) {
            startDate = (java.util.Date) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStartDate().compareTo(startDate) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "moreThanOdi", sequence = 1, ignoreException = false, hawkParam = "var innings, var odi", definition = "Filters Inning records where Odi is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanOdi(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer odi = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                odi = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            odi = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getOdi().compareTo(odi) > 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "avgRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Runs across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgRuns(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getRuns()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Mins across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgMins(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getMins()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of BallFaced across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getBallFaced()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Fours across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgFours(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getFours()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Sixes across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgSixes(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getSixes()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of StrikeRate across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getStrikeRate()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgPositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Positions across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgPositions(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getPositions()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Innings across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgInnings(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getInnings()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "avgOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the arithmetic mean of Odi across Inning records. Arguments: (var innings?). If an innings set is provided, the average is calculated over that set; otherwise it applies to all available Inning records. Returns a rounded double value using scale 2 and HALF_UP rounding. Does not mutate input. Designed for composable aggregation in Hawk DSL analytics.")
    public double avgOdi(Object... args) {
        Set<Inning> input = null;
        if (args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int i = 0;
        BigDecimal total = BigDecimal.ZERO;
        double avg = 0.0;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getOdi()));
            i++;
        }
        if (i > 0) {
            avg = total.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        return avg;
    }

    @SubTask(name = "lessThanPlayer ", sequence = 1, ignoreException = false, hawkParam = "var innings,var player ")
    public Set<Inning> lessThanPlayer(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Player player = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                player = (org.cricket.hawkeye.db.Player) args[1];
            }
        } else if (args.length == 1) {
            player = (org.cricket.hawkeye.db.Player) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getPlayer().compareTo(player) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Set<Inning> lessThanRuns(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer runs = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                runs = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            runs = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getRuns().compareTo(runs) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Set<Inning> lessThanMins(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer mins = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                mins = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            mins = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getMins().compareTo(mins) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Set<Inning> lessThanBallFaced(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer ballFaced = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                ballFaced = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            ballFaced = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getBallFaced().compareTo(ballFaced) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Set<Inning> lessThanFours(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer fours = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                fours = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            fours = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getFours().compareTo(fours) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Set<Inning> lessThanSixes(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer sixes = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                sixes = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            sixes = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getSixes().compareTo(sixes) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Set<Inning> lessThanStrikeRate(Object... args) {
        Set<Inning> input = null;
        java.lang.Double strikeRate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                strikeRate = (java.lang.Double) args[1];
            }
        } else if (args.length == 1) {
            strikeRate = (java.lang.Double) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStrikeRate().compareTo(strikeRate) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Set<Inning> lessThanPositions(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer positions = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                positions = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            positions = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getPositions().compareTo(positions) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Set<Inning> lessThanInnings(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer innings = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                innings = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            innings = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getInnings().compareTo(innings) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanOpposition ", sequence = 1, ignoreException = false, hawkParam = "var innings,var opposition ")
    public Set<Inning> lessThanOpposition(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Country opposition = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                opposition = (org.cricket.hawkeye.db.Country) args[1];
            }
        } else if (args.length == 1) {
            opposition = (org.cricket.hawkeye.db.Country) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getOpposition().compareTo(opposition) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanGround ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ground ")
    public Set<Inning> lessThanGround(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Ground ground = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                ground = (org.cricket.hawkeye.db.Ground) args[1];
            }
        } else if (args.length == 1) {
            ground = (org.cricket.hawkeye.db.Ground) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getGround().compareTo(ground) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
    public Set<Inning> lessThanStartDate(Object... args) {
        Set<Inning> input = null;
        java.util.Date startDate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                startDate = (java.util.Date) args[1];
            }
        } else if (args.length == 1) {
            startDate = (java.util.Date) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStartDate().compareTo(startDate) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "lessThanOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Set<Inning> lessThanOdi(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer odi = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                odi = (java.lang.Integer) args[1];
            }
        } else if (args.length == 1) {
            odi = (java.lang.Integer) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getOdi().compareTo(odi) < 0) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "sortPlayer", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Player. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortPlayer(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getPlayer);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Runs. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortRuns(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getRuns);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Mins. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortMins(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getMins);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by BallFaced. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getBallFaced);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Fours. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortFours(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getFours);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Sixes. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortSixes(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getSixes);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by StrikeRate. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getStrikeRate);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortPositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Positions. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortPositions(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getPositions);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortDismissalType", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by DismissalType. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortDismissalType(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getDismissalType);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Innings. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getInnings);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Opposition. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortOpposition(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getOpposition);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortGround", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Ground. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortGround(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getGround);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by StartDate. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortStartDate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getStartDate);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Odi. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortOdi(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getOdi);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortWasOut", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by WasOut. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortWasOut(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getWasOut);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortBatted", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns Inning records sorted ascending by Batted. Arguments: (var innings?). If an innings set is provided, sorting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new ordered array without mutating input. Designed for composable ordering in Hawk DSL analytics.")
    public Inning[] sortBatted(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getBatted);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "beforeStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings, var startDate", definition = "Filters Inning records where StartDate occurs strictly before the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating the input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> beforeStartDate(Object... args) {
        Set<Inning> input = null;
        java.util.Date startDate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                startDate = (java.util.Date) args[1];
            }
        } else if (args.length == 1) {
            startDate = (java.util.Date) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStartDate().before(startDate)) {
                result.add(inning);
            }
        }
        if (input != null && !input.isEmpty()) {
            result.retainAll(input);
        }
        return result;
    }

    @SubTask(name = "likePlayerName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Player wrapper for nested comparison and delegates to likePlayer. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likePlayerName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Player player = new org.cricket.hawkeye.db.Player();
        player.setName(name);
        return likePlayer(input, player);
    }

    @SubTask(name = "likeOppositionName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Country wrapper for nested comparison and delegates to likeOpposition. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likeOppositionName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Country opposition = new org.cricket.hawkeye.db.Country();
        opposition.setName(name);
        return likeOpposition(input, opposition);
    }

    @SubTask(name = "likeGroundName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Ground wrapper for nested comparison and delegates to likeGround. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likeGroundName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Ground ground = new org.cricket.hawkeye.db.Ground();
        ground.setName(name);
        return likeGround(input, ground);
    }

    @SubTask(name = "stddevRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Runs across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceRuns and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevRuns(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceRuns(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Mins across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceMins and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevMins(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceMins(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of BallFaced across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceBallFaced and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceBallFaced(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Fours across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceFours and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevFours(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceFours(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Sixes across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceSixes and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevSixes(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceSixes(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of StrikeRate across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceStrikeRate and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceStrikeRate(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevPositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Positions across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to variancePositions and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevPositions(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.variancePositions(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Innings across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceInnings and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceInnings(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the standard deviation of Odi across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Internally delegates to varianceOdi and applies square root with scale 2 and HALF_UP rounding. Returns a rounded double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double stddevOdi(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        double variance = this.varianceOdi(input);
        double stddev = new BigDecimal(Math.sqrt(variance)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "bottomRuns", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Runs. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomRuns(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortRuns(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomMins", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Mins. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomMins(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortMins(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by BallFaced. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomBallFaced(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortBallFaced(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomFours", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Fours. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomFours(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortFours(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomSixes", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Sixes. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomSixes(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortSixes(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by StrikeRate. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomStrikeRate(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortStrikeRate(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomPositions", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Positions. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomPositions(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortPositions(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomInnings", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Innings. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomInnings(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortInnings(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "bottomOdi", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the lowest 'count' Inning records ordered ascending by Odi. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] bottomOdi(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortOdi(input);
        if (tmpArray == null) {
            return null;
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "likePlayer", sequence = 1, ignoreException = false, hawkParam = "var innings, var player", definition = "Filters Inning records where Player matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likePlayer(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Player player = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            player = (org.cricket.hawkeye.db.Player) args[1];
        }
        Inning otherInning = new Inning();
        otherInning.setPlayer(player);
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : baseSet) {
            if (0 == 1) {
                if (inning.like(otherInning)) {
                    result.add(inning);
                }
            }
        }
        return result;
    }

    @SubTask(name = "likeOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings, var opposition", definition = "Filters Inning records where Opposition matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likeOpposition(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Country opposition = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            opposition = (org.cricket.hawkeye.db.Country) args[1];
        }
        Inning otherInning = new Inning();
        otherInning.setOpposition(opposition);
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : baseSet) {
            if (0 == 1) {
                if (inning.like(otherInning)) {
                    result.add(inning);
                }
            }
        }
        return result;
    }

    @SubTask(name = "likeGround", sequence = 1, ignoreException = false, hawkParam = "var innings, var ground", definition = "Filters Inning records where Ground matches the provided pattern using like semantics. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> likeGround(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Ground ground = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            ground = (org.cricket.hawkeye.db.Ground) args[1];
        }
        Inning otherInning = new Inning();
        otherInning.setGround(ground);
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : baseSet) {
            if (0 == 1) {
                if (inning.like(otherInning)) {
                    result.add(inning);
                }
            }
        }
        return result;
    }

    @SubTask(name = "sumRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Runs across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumRuns(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getRuns()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Mins across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumMins(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getMins()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of BallFaced across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getBallFaced()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Fours across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumFours(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getFours()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Sixes across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumSixes(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getSixes()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of StrikeRate across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getStrikeRate()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumPositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Positions across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumPositions(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getPositions()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Innings across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getInnings()));
        }
        return total.intValue();
    }

    @SubTask(name = "sumOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the total sum of Odi across Inning records. Arguments: (var innings?). If an innings set is provided, summation is restricted to that set; otherwise it applies to all available Inning records. Returns an integer sum without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int sumOdi(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            total = total.add(new BigDecimal(inning.getOdi()));
        }
        return total.intValue();
    }

    @SubTask(name = "countDismissalType", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with DismissalType. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countDismissalType(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with Innings. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with Opposition. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countOpposition(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countGround", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with Ground. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countGround(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with StartDate. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countStartDate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countWasOut", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with WasOut. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countWasOut(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countBatted", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Counts the number of Inning records associated with Batted. Arguments: (var innings?). If an innings set is provided, counting is restricted to that set; otherwise the operation applies to all available Inning records. Returns a deterministic integer result without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public int countBatted(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "maxRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Inning maxRuns(Object... args) {
        Inning[] tmpArray = this.sortRuns(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Inning maxMins(Object... args) {
        Inning[] tmpArray = this.sortMins(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Inning maxBallFaced(Object... args) {
        Inning[] tmpArray = this.sortBallFaced(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Inning maxFours(Object... args) {
        Inning[] tmpArray = this.sortFours(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Inning maxSixes(Object... args) {
        Inning[] tmpArray = this.sortSixes(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Inning maxStrikeRate(Object... args) {
        Inning[] tmpArray = this.sortStrikeRate(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Inning maxPositions(Object... args) {
        Inning[] tmpArray = this.sortPositions(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Inning maxInnings(Object... args) {
        Inning[] tmpArray = this.sortInnings(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "maxOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Inning maxOdi(Object... args) {
        Inning[] tmpArray = this.sortOdi(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[tmpArray.length - 1];
        return result;
    }

    @SubTask(name = "topRuns", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Runs. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topRuns(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortRuns(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topMins", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Mins. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topMins(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortMins(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by BallFaced. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topBallFaced(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortBallFaced(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topFours", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Fours. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topFours(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortFours(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topSixes", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Sixes. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topSixes(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortSixes(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by StrikeRate. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topStrikeRate(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortStrikeRate(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topPositions", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Positions. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topPositions(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortPositions(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topInnings", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Innings. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topInnings(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortInnings(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "topOdi", sequence = 1, ignoreException = false, hawkParam = "var innings, var count", definition = "Returns the highest 'count' Inning records ordered ascending by Odi. Arguments: (var innings?, var count?). If an innings set is provided as the first argument, selection is restricted to that set; otherwise the operation applies to all available Inning records. If count is not provided, defaults to 10. Returns a new array without mutating input. Designed for composable ranking queries in Hawk DSL.")
    public Inning[] topOdi(Object... args) {
        Integer count = 10;
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            count = (Integer) args[1];
        }
        Inning[] tmpArray = this.sortOdi(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        count = Math.min(count, tmpArray.length);
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "equalPlayerName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Player wrapper for nested comparison and delegates to equalPlayer. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalPlayerName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Player player = new org.cricket.hawkeye.db.Player();
        player.setName(name);
        return equalPlayer(input, player);
    }

    @SubTask(name = "equalOppositionName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Country wrapper for nested comparison and delegates to equalOpposition. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalOppositionName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Country opposition = new org.cricket.hawkeye.db.Country();
        opposition.setName(name);
        return equalOpposition(input, opposition);
    }

    @SubTask(name = "equalGroundName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Ground wrapper for nested comparison and delegates to equalGround. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalGroundName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            name = (java.lang.String) args[1];
        }
        org.cricket.hawkeye.db.Ground ground = new org.cricket.hawkeye.db.Ground();
        ground.setName(name);
        return equalGround(input, ground);
    }

    @SubTask(name = "minRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Runs. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minRuns(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortRuns(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Mins. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minMins(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortMins(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of BallFaced. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortBallFaced(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Fours. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minFours(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortFours(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Sixes. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minSixes(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortSixes(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of StrikeRate. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortStrikeRate(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minPositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Positions. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minPositions(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortPositions(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Innings. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortInnings(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "minOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Returns the Inning record with the minimum value of Odi. Arguments: (var innings?). If an innings set is provided, selection is restricted to that set; otherwise the operation applies to all available Inning records. Returns a single record without mutating input. Designed for composable aggregation in Hawk DSL analytics.")
    public Inning minOdi(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Inning[] tmpArray = this.sortOdi(input);
        if (tmpArray == null || tmpArray.length == 0) {
            return null;
        }
        return tmpArray[0];
    }

    @SubTask(name = "varianceRuns", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Runs across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceRuns(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getRuns());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgRuns(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceMins", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Mins across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceMins(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getMins());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgMins(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of BallFaced across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceBallFaced(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getBallFaced());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgBallFaced(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceFours", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Fours across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceFours(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getFours());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgFours(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceSixes", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Sixes across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceSixes(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getSixes());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgSixes(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of StrikeRate across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceStrikeRate(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getStrikeRate());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgStrikeRate(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "variancePositions", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Positions across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double variancePositions(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getPositions());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgPositions(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceInnings", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Innings across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceInnings(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getInnings());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgInnings(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceOdi", sequence = 1, ignoreException = false, hawkParam = "var innings", definition = "Computes the population variance of Odi across Inning records. Arguments: (var innings?). If an innings set is provided, computation is restricted to that set; otherwise it applies to all available Inning records. Uses E[X^2] - (E[X])^2 formulation with scale 2 and HALF_UP rounding. Returns a double value without mutating input. Designed for composable statistical aggregation in Hawk DSL analytics.")
    public double varianceOdi(Object... args) {
        Set<Inning> input = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        Set<Inning> tmpSet;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        int count = 0;
        BigDecimal squaredTotal = BigDecimal.ZERO;
        for (Inning inning : tmpSet) {
            BigDecimal value = new BigDecimal(inning.getOdi());
            squaredTotal = squaredTotal.add(value.multiply(value));
            count++;
        }
        double squaredAvg = 0.0;
        if (count > 0) {
            squaredAvg = squaredTotal.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgOdi(input);
        double variance = squaredAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "equalPlayer", sequence = 1, ignoreException = false, hawkParam = "var innings, var player", definition = "Filters Inning records where Player equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalPlayer(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Player player = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            player = (org.cricket.hawkeye.db.Player) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getPlayer().equals(player)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalRuns", sequence = 1, ignoreException = false, hawkParam = "var innings, var runs", definition = "Filters Inning records where Runs equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalRuns(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer runs = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            runs = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getRuns().equals(runs)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalMins", sequence = 1, ignoreException = false, hawkParam = "var innings, var mins", definition = "Filters Inning records where Mins equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalMins(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer mins = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            mins = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getMins().equals(mins)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalBallFaced", sequence = 1, ignoreException = false, hawkParam = "var innings, var ballFaced", definition = "Filters Inning records where BallFaced equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalBallFaced(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer ballFaced = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            ballFaced = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getBallFaced().equals(ballFaced)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalFours", sequence = 1, ignoreException = false, hawkParam = "var innings, var fours", definition = "Filters Inning records where Fours equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalFours(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer fours = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            fours = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getFours().equals(fours)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalSixes", sequence = 1, ignoreException = false, hawkParam = "var innings, var sixes", definition = "Filters Inning records where Sixes equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalSixes(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer sixes = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            sixes = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getSixes().equals(sixes)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalStrikeRate", sequence = 1, ignoreException = false, hawkParam = "var innings, var strikeRate", definition = "Filters Inning records where StrikeRate equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalStrikeRate(Object... args) {
        Set<Inning> input = null;
        java.lang.Double strikeRate = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            strikeRate = (java.lang.Double) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getStrikeRate().equals(strikeRate)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalPositions", sequence = 1, ignoreException = false, hawkParam = "var innings, var positions", definition = "Filters Inning records where Positions equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalPositions(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer positions = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            positions = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getPositions().equals(positions)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalDismissalType", sequence = 1, ignoreException = false, hawkParam = "var innings, var dismissalType", definition = "Filters Inning records where DismissalType equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalDismissalType(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.codegen.constant.DismissalType dismissalType = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            dismissalType = (org.cricket.hawkeye.codegen.constant.DismissalType) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getDismissalType().equals(dismissalType)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalInnings", sequence = 1, ignoreException = false, hawkParam = "var innings, var innings", definition = "Filters Inning records where Innings equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalInnings(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer innings = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            innings = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getInnings().equals(innings)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings, var opposition", definition = "Filters Inning records where Opposition equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalOpposition(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Country opposition = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            opposition = (org.cricket.hawkeye.db.Country) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getOpposition().equals(opposition)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalGround", sequence = 1, ignoreException = false, hawkParam = "var innings, var ground", definition = "Filters Inning records where Ground equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalGround(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.db.Ground ground = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            ground = (org.cricket.hawkeye.db.Ground) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getGround().equals(ground)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings, var startDate", definition = "Filters Inning records where StartDate equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalStartDate(Object... args) {
        Set<Inning> input = null;
        java.util.Date startDate = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            startDate = (java.util.Date) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getStartDate().equals(startDate)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalOdi", sequence = 1, ignoreException = false, hawkParam = "var innings, var odi", definition = "Filters Inning records where Odi equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalOdi(Object... args) {
        Set<Inning> input = null;
        java.lang.Integer odi = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            odi = (java.lang.Integer) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getOdi().equals(odi)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalWasOut", sequence = 1, ignoreException = false, hawkParam = "var innings, var wasOut", definition = "Filters Inning records where WasOut equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalWasOut(Object... args) {
        Set<Inning> input = null;
        java.lang.Boolean wasOut = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            wasOut = (java.lang.Boolean) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getWasOut().equals(wasOut)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "equalBatted", sequence = 1, ignoreException = false, hawkParam = "var innings, var batted", definition = "Filters Inning records where Batted equals the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> equalBatted(Object... args) {
        Set<Inning> input = null;
        java.lang.Boolean batted = null;
        if (args.length >= 1 && args[0] != null) {
            input = (Set<Inning>) args[0];
        }
        if (args.length >= 2 && args[1] != null) {
            batted = (java.lang.Boolean) args[1];
        }
        Set<Inning> result = new TreeSet<Inning>();
        Set<Inning> baseSet;
        if (input != null && !input.isEmpty()) {
            baseSet = input;
        } else {
            baseSet = this.getInnings().getAll();
        }
        for (Inning inning : baseSet) {
            if (inning.getBatted().equals(batted)) {
                result.add(inning);
            }
        }
        return result;
    }

    @SubTask(name = "moreThanPlayerName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Player wrapper for nested comparison and delegates to moreThanPlayer. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanPlayerName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                name = (java.lang.String) args[1];
            }
        } else if (args.length == 1) {
            name = (java.lang.String) args[0];
        }
        org.cricket.hawkeye.db.Player player = new org.cricket.hawkeye.db.Player();
        player.setName(name);
        return moreThanPlayer(input, player);
    }

    @SubTask(name = "moreThanOppositionName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Country wrapper for nested comparison and delegates to moreThanOpposition. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanOppositionName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                name = (java.lang.String) args[1];
            }
        } else if (args.length == 1) {
            name = (java.lang.String) args[0];
        }
        org.cricket.hawkeye.db.Country opposition = new org.cricket.hawkeye.db.Country();
        opposition.setName(name);
        return moreThanOpposition(input, opposition);
    }

    @SubTask(name = "moreThanGroundName", sequence = 1, ignoreException = false, hawkParam = "var innings, var name", definition = "Filters Inning records where nested field Name is strictly greater than the provided value. Arguments: (var innings?, var value). If an innings set is provided as the first argument, filtering is restricted to that set; otherwise the operation applies to all available Inning records. Internally constructs a org.cricket.hawkeye.db.Ground wrapper for nested comparison and delegates to moreThanGround. Returns a new result set without mutating input. Designed for composable query chaining in Hawk DSL.")
    public Set<Inning> moreThanGroundName(Object... args) {
        Set<Inning> input = null;
        java.lang.String name = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                name = (java.lang.String) args[1];
            }
        } else if (args.length == 1) {
            name = (java.lang.String) args[0];
        }
        org.cricket.hawkeye.db.Ground ground = new org.cricket.hawkeye.db.Ground();
        ground.setName(name);
        return moreThanGround(input, ground);
    }
}
