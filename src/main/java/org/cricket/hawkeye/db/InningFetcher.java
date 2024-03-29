/**
 * This file was generated at Tue Feb 23 13:48:11 IST 2021*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) BigBang<->BigCrunch
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

    @SubTask(name = "afterStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
    public Set<Inning> afterStartDate(Object... args) {
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
            if (inning.getStartDate().after(startDate)) {
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

    @SubTask(name = "lessThanPlayerName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> lessThanPlayerName(Object... args) {
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
        return lessThanPlayer(input, player);
    }

    @SubTask(name = "lessThanOppositionName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> lessThanOppositionName(Object... args) {
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
        return lessThanOpposition(input, opposition);
    }

    @SubTask(name = "lessThanGroundName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> lessThanGroundName(Object... args) {
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
        return lessThanGround(input, ground);
    }

    @SubTask(name = "moreThanPlayer ", sequence = 1, ignoreException = false, hawkParam = "var innings,var player ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Set<Inning> moreThanStrikeRate(Object... args) {
        Set<Inning> input = null;
        java.lang.Float strikeRate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                strikeRate = (java.lang.Float) args[1];
            }
        } else if (args.length == 1) {
            strikeRate = (java.lang.Float) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStrikeRate().compareTo(strikeRate) > 0) {
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

    @SubTask(name = "moreThanPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanOpposition ", sequence = 1, ignoreException = false, hawkParam = "var innings,var opposition ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanGround ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ground ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "moreThanOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "avgRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
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

    @SubTask(name = "avgMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
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

    @SubTask(name = "avgBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
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

    @SubTask(name = "avgFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
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

    @SubTask(name = "avgSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
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

    @SubTask(name = "avgStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
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

    @SubTask(name = "avgPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
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

    @SubTask(name = "avgInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
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

    @SubTask(name = "avgOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
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
        java.lang.Float strikeRate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                strikeRate = (java.lang.Float) args[1];
            }
        } else if (args.length == 1) {
            strikeRate = (java.lang.Float) args[0];
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

    @SubTask(name = "sortPlayer ", sequence = 1, ignoreException = false, hawkParam = "var innings,var player ")
    public Inning[] sortPlayer(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getPlayer);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Inning[] sortRuns(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getRuns);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Inning[] sortMins(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getMins);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Inning[] sortBallFaced(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getBallFaced);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Inning[] sortFours(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getFours);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Inning[] sortSixes(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getSixes);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Inning[] sortStrikeRate(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getStrikeRate);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Inning[] sortPositions(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getPositions);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortDismissalType ", sequence = 1, ignoreException = false, hawkParam = "var innings,var dismissalType ")
    public Inning[] sortDismissalType(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getDismissalType);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Inning[] sortInnings(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getInnings);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortOpposition ", sequence = 1, ignoreException = false, hawkParam = "var innings,var opposition ")
    public Inning[] sortOpposition(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getOpposition);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortGround ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ground ")
    public Inning[] sortGround(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getGround);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
    public Inning[] sortStartDate(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getStartDate);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Inning[] sortOdi(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getOdi);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortWasOut ", sequence = 1, ignoreException = false, hawkParam = "var innings,var wasOut ")
    public Inning[] sortWasOut(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getWasOut);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "sortBatted ", sequence = 1, ignoreException = false, hawkParam = "var innings,var batted ")
    public Inning[] sortBatted(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Set<Inning> input = null;
        input = (Set<Inning>) args[0];
        Set<Inning> tmpSet = null;
        if (input != null && !input.isEmpty()) {
            tmpSet = input;
        } else {
            tmpSet = this.getInnings().getAll();
        }
        Comparator<Inning> cmp = java.util.Comparator.comparing(Inning::getBatted);
        return tmpSet.stream().sorted(cmp).toArray(size -> new Inning[size]);
    }

    @SubTask(name = "beforeStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
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
            if (input.retainAll(result)) {
                result = input;
            } else {
                result = new TreeSet<Inning>();
            }
        }
        return result;
    }

    @SubTask(name = "likePlayerName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> likePlayerName(Object... args) {
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
        return likePlayer(input, player);
    }

    @SubTask(name = "likeOppositionName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> likeOppositionName(Object... args) {
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
        return likeOpposition(input, opposition);
    }

    @SubTask(name = "likeGroundName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> likeGroundName(Object... args) {
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
        return likeGround(input, ground);
    }

    @SubTask(name = "stddevRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevRuns(Object... args) {
        double variance = this.varianceRuns(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevMins ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevMins(Object... args) {
        double variance = this.varianceMins(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevBallFaced(Object... args) {
        double variance = this.varianceBallFaced(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevFours ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevFours(Object... args) {
        double variance = this.varianceFours(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevSixes(Object... args) {
        double variance = this.varianceSixes(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevStrikeRate(Object... args) {
        double variance = this.varianceStrikeRate(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevPositions(Object... args) {
        double variance = this.variancePositions(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevInnings(Object... args) {
        double variance = this.varianceInnings(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "stddevOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double stddevOdi(Object... args) {
        double variance = this.varianceOdi(args);
        double stddev = (new BigDecimal(Math.sqrt(variance))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return stddev;
    }

    @SubTask(name = "bottomRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Inning[] bottomRuns(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortRuns(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Inning[] bottomMins(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortMins(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Inning[] bottomBallFaced(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortBallFaced(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Inning[] bottomFours(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortFours(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Inning[] bottomSixes(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortSixes(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Inning[] bottomStrikeRate(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortStrikeRate(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Inning[] bottomPositions(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortPositions(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Inning[] bottomInnings(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortInnings(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "bottomOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Inning[] bottomOdi(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortOdi(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[tmpArray.length - i - 1];
        }
        return result;
    }

    @SubTask(name = "likePlayer ", sequence = 1, ignoreException = false, hawkParam = "var innings,var player ")
    public Set<Inning> likePlayer(Object... args) {
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
        Inning otherInning = new Inning();
        otherInning.setPlayer(player);
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getPlayer().like(otherInning.getPlayer())) {
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

    @SubTask(name = "likeOpposition ", sequence = 1, ignoreException = false, hawkParam = "var innings,var opposition ")
    public Set<Inning> likeOpposition(Object... args) {
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
        Inning otherInning = new Inning();
        otherInning.setOpposition(opposition);
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getOpposition().like(otherInning.getOpposition())) {
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

    @SubTask(name = "likeGround ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ground ")
    public Set<Inning> likeGround(Object... args) {
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
        Inning otherInning = new Inning();
        otherInning.setGround(ground);
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getGround().like(otherInning.getGround())) {
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

    @SubTask(name = "sumRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumRuns(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getRuns();
        }
        return total.intValue();
    }

    @SubTask(name = "sumMins ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumMins(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getMins();
        }
        return total.intValue();
    }

    @SubTask(name = "sumBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumBallFaced(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getBallFaced();
        }
        return total.intValue();
    }

    @SubTask(name = "sumFours ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumFours(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getFours();
        }
        return total.intValue();
    }

    @SubTask(name = "sumSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumSixes(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getSixes();
        }
        return total.intValue();
    }

    @SubTask(name = "sumStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumStrikeRate(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getStrikeRate();
        }
        return total.intValue();
    }

    @SubTask(name = "sumPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumPositions(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getPositions();
        }
        return total.intValue();
    }

    @SubTask(name = "sumInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumInnings(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getInnings();
        }
        return total.intValue();
    }

    @SubTask(name = "sumOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int sumOdi(Object... args) {
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
        Double total = 0.0;
        for (Inning inning : tmpSet) {
            total = total + inning.getOdi();
        }
        return total.intValue();
    }

    @SubTask(name = "countDismissalType", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countDismissalType(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countInnings", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countInnings(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countOpposition", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countOpposition(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countGround", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countGround(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countStartDate", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countStartDate(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countWasOut", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countWasOut(Object... args) {
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
        return (int) tmpSet.stream().count();
    }

    @SubTask(name = "countBatted", sequence = 1, ignoreException = false, hawkParam = "var innings")
    public int countBatted(Object... args) {
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

    @SubTask(name = "topRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Inning[] topRuns(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortRuns(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Inning[] topMins(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortMins(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Inning[] topBallFaced(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortBallFaced(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Inning[] topFours(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortFours(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Inning[] topSixes(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortSixes(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Inning[] topStrikeRate(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortStrikeRate(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Inning[] topPositions(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortPositions(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Inning[] topInnings(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortInnings(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "topOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Inning[] topOdi(Object... args) {
        Integer count = 10;
        Inning[] tmpArray = this.sortOdi(args);
        if (tmpArray == null) {
            return null;
        }
        if (args.length == 2) {
            count = (Integer) args[1];
        }
        Inning[] result = new Inning[count];
        for (int i = 0; i < count; i++) {
            result[i] = tmpArray[i];
        }
        return result;
    }

    @SubTask(name = "equalPlayerName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> equalPlayerName(Object... args) {
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
        return equalPlayer(input, player);
    }

    @SubTask(name = "equalOppositionName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> equalOppositionName(Object... args) {
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
        return equalOpposition(input, opposition);
    }

    @SubTask(name = "equalGroundName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
    public Set<Inning> equalGroundName(Object... args) {
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
        return equalGround(input, ground);
    }

    @SubTask(name = "minRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Inning minRuns(Object... args) {
        Inning[] tmpArray = this.sortRuns(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Inning minMins(Object... args) {
        Inning[] tmpArray = this.sortMins(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Inning minBallFaced(Object... args) {
        Inning[] tmpArray = this.sortBallFaced(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Inning minFours(Object... args) {
        Inning[] tmpArray = this.sortFours(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Inning minSixes(Object... args) {
        Inning[] tmpArray = this.sortSixes(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Inning minStrikeRate(Object... args) {
        Inning[] tmpArray = this.sortStrikeRate(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Inning minPositions(Object... args) {
        Inning[] tmpArray = this.sortPositions(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Inning minInnings(Object... args) {
        Inning[] tmpArray = this.sortInnings(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "minOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Inning minOdi(Object... args) {
        Inning[] tmpArray = this.sortOdi(args);
        if (tmpArray == null) {
            return null;
        }
        Inning result = tmpArray[0];
        return result;
    }

    @SubTask(name = "varianceRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceRuns(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getRuns() * inning.getRuns()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgRuns(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceMins ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceMins(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getMins() * inning.getMins()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgMins(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceBallFaced(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getBallFaced() * inning.getBallFaced()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgBallFaced(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceFours ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceFours(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getFours() * inning.getFours()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgFours(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceSixes(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getSixes() * inning.getSixes()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgSixes(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceStrikeRate(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getStrikeRate() * inning.getStrikeRate()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgStrikeRate(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "variancePositions ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double variancePositions(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getPositions() * inning.getPositions()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgPositions(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceInnings(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getInnings() * inning.getInnings()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgInnings(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "varianceOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings ")
    public double varianceOdi(Object... args) {
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
        BigDecimal squaredTotal = BigDecimal.ZERO;
        double sqauredTotalAvg = 0.0;
        for (Inning inning : tmpSet) {
            squaredTotal = squaredTotal.add(new BigDecimal(inning.getOdi() * inning.getOdi()));
            i++;
        }
        if (i > 0) {
            sqauredTotalAvg = squaredTotal.divide(new BigDecimal(i), 2, RoundingMode.HALF_UP).doubleValue();
        }
        double avg = this.avgOdi(args);
        double variance = sqauredTotalAvg - (avg * avg);
        return variance;
    }

    @SubTask(name = "equalPlayer ", sequence = 1, ignoreException = false, hawkParam = "var innings,var player ")
    public Set<Inning> equalPlayer(Object... args) {
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
            if (inning.getPlayer().equals(player)) {
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

    @SubTask(name = "equalRuns ", sequence = 1, ignoreException = false, hawkParam = "var innings,var runs ")
    public Set<Inning> equalRuns(Object... args) {
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
            if (inning.getRuns().equals(runs)) {
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

    @SubTask(name = "equalMins ", sequence = 1, ignoreException = false, hawkParam = "var innings,var mins ")
    public Set<Inning> equalMins(Object... args) {
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
            if (inning.getMins().equals(mins)) {
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

    @SubTask(name = "equalBallFaced ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ballFaced ")
    public Set<Inning> equalBallFaced(Object... args) {
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
            if (inning.getBallFaced().equals(ballFaced)) {
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

    @SubTask(name = "equalFours ", sequence = 1, ignoreException = false, hawkParam = "var innings,var fours ")
    public Set<Inning> equalFours(Object... args) {
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
            if (inning.getFours().equals(fours)) {
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

    @SubTask(name = "equalSixes ", sequence = 1, ignoreException = false, hawkParam = "var innings,var sixes ")
    public Set<Inning> equalSixes(Object... args) {
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
            if (inning.getSixes().equals(sixes)) {
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

    @SubTask(name = "equalStrikeRate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var strikeRate ")
    public Set<Inning> equalStrikeRate(Object... args) {
        Set<Inning> input = null;
        java.lang.Float strikeRate = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                strikeRate = (java.lang.Float) args[1];
            }
        } else if (args.length == 1) {
            strikeRate = (java.lang.Float) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getStrikeRate().equals(strikeRate)) {
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

    @SubTask(name = "equalPositions ", sequence = 1, ignoreException = false, hawkParam = "var innings,var positions ")
    public Set<Inning> equalPositions(Object... args) {
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
            if (inning.getPositions().equals(positions)) {
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

    @SubTask(name = "equalDismissalType ", sequence = 1, ignoreException = false, hawkParam = "var innings,var dismissalType ")
    public Set<Inning> equalDismissalType(Object... args) {
        Set<Inning> input = null;
        org.cricket.hawkeye.codegen.constant.DismissalType dismissalType = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                dismissalType = (org.cricket.hawkeye.codegen.constant.DismissalType) args[1];
            }
        } else if (args.length == 1) {
            dismissalType = (org.cricket.hawkeye.codegen.constant.DismissalType) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getDismissalType().equals(dismissalType)) {
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

    @SubTask(name = "equalInnings ", sequence = 1, ignoreException = false, hawkParam = "var innings,var innings ")
    public Set<Inning> equalInnings(Object... args) {
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
            if (inning.getInnings().equals(innings)) {
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

    @SubTask(name = "equalOpposition ", sequence = 1, ignoreException = false, hawkParam = "var innings,var opposition ")
    public Set<Inning> equalOpposition(Object... args) {
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
            if (inning.getOpposition().equals(opposition)) {
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

    @SubTask(name = "equalGround ", sequence = 1, ignoreException = false, hawkParam = "var innings,var ground ")
    public Set<Inning> equalGround(Object... args) {
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
            if (inning.getGround().equals(ground)) {
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

    @SubTask(name = "equalStartDate ", sequence = 1, ignoreException = false, hawkParam = "var innings,var startDate ")
    public Set<Inning> equalStartDate(Object... args) {
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
            if (inning.getStartDate().equals(startDate)) {
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

    @SubTask(name = "equalOdi ", sequence = 1, ignoreException = false, hawkParam = "var innings,var odi ")
    public Set<Inning> equalOdi(Object... args) {
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
            if (inning.getOdi().equals(odi)) {
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

    @SubTask(name = "equalWasOut ", sequence = 1, ignoreException = false, hawkParam = "var innings,var wasOut ")
    public Set<Inning> equalWasOut(Object... args) {
        Set<Inning> input = null;
        java.lang.Boolean wasOut = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                wasOut = (java.lang.Boolean) args[1];
            }
        } else if (args.length == 1) {
            wasOut = (java.lang.Boolean) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getWasOut().equals(wasOut)) {
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

    @SubTask(name = "equalBatted ", sequence = 1, ignoreException = false, hawkParam = "var innings,var batted ")
    public Set<Inning> equalBatted(Object... args) {
        Set<Inning> input = null;
        java.lang.Boolean batted = null;
        if (args == null || args.length == 0) {
            return null;
        }
        if (args.length == 2) {
            if (args[0] != null) {
                input = (Set<Inning>) args[0];
            }
            if (args[1] != null) {
                batted = (java.lang.Boolean) args[1];
            }
        } else if (args.length == 1) {
            batted = (java.lang.Boolean) args[0];
        }
        Set<Inning> result = new TreeSet<Inning>();
        for (Inning inning : this.getInnings().getAll()) {
            if (inning.getBatted().equals(batted)) {
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

    @SubTask(name = "moreThanPlayerName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
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

    @SubTask(name = "moreThanOppositionName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
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

    @SubTask(name = "moreThanGroundName ", sequence = 1, ignoreException = false, hawkParam = "var innings ,var name ")
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
