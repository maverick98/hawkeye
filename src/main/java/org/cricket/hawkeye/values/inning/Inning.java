package org.cricket.hawkeye.values.inning;

import org.cricket.hawkeye.values.player.Player;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.codegen.annotation.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.cricket.hawkeye.codegen.annotation.AggregateClause;
import org.cricket.hawkeye.codegen.annotation.ComparisionClause;
import org.cricket.hawkeye.codegen.constant.DismissalType;
import org.cricket.hawkeye.codegen.constant.MatchType;
import org.cricket.hawkeye.codegen.SourceVO;

/**
 *
 * @author msahu
 */
@Entity(ownLikeImpl = false)
public class Inning implements SourceVO {

    @HQLGenerate(
            field = "name",
            comparision =
            @ComparisionClause(moreThan = true, lessThan = true,after = false ,before = false ),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = false,
            sum = false,
            stddev = false,
            variance = false))
    public org.cricket.hawkeye.db.Player player = new org.cricket.hawkeye.db.Player();
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer runs = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer mins = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer ballFaced = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer fours = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer sixes = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Float strikeRate = new Float(0.0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer positions = new Integer(0);
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false,
            moreThan = false,
            lessThan = false),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public DismissalType dismissalType = new DismissalType();
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause(count = true)
            )
    public Integer innings = new Integer(0);
    @HQLGenerate(
            field = "name",
            comparision =
            @ComparisionClause(moreThan = true, lessThan = true,after =false ,before = false ),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public org.cricket.hawkeye.db.Country opposition = new org.cricket.hawkeye.db.Country();
    @HQLGenerate(
            field = "name",
            comparision =
            @ComparisionClause(moreThan = true, lessThan = true,after = false ,before = false ),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public org.cricket.hawkeye.db.Ground ground = new org.cricket.hawkeye.db.Ground();
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            like = false),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public Date startDate = new Date();
    @HQLGenerate(
            comparision =
            @ComparisionClause(
            after = false,
            before = false,
            like = false),
            aggregate =@AggregateClause
            )
    public Integer odi = new Integer(0);
     @HQLGenerate(
            comparision =
            @ComparisionClause(moreThan = false, lessThan = false,after = false ,before = false , like = false),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public Boolean wasOut = Boolean.TRUE;
    @HQLGenerate(
            comparision =
            @ComparisionClause(moreThan = false, lessThan = false,after = false ,before = false , like = false),
            aggregate =
            @AggregateClause(
            avg = false,
            max = false,
            min = false,
            top = false,
            bottom = false,
            count = true,
            sum = false,
            stddev = false,
            variance = false))
    public Boolean batted = Boolean.TRUE;
    public MatchType matchType;

    public Integer getBallFaced() {
        return ballFaced;
    }

    public void setBallFaced(Integer ballFaced) {
        this.ballFaced = ballFaced;
    }

    public Boolean getBatted() {
        return batted;
    }

    public void setBatted(Boolean batted) {
        this.batted = batted;
    }

    public DismissalType getDismissalType() {
        return dismissalType;
    }

    public void setDismissalType(DismissalType dismissalType) {
        this.dismissalType = dismissalType;
    }

    public Integer getFours() {
        return fours;
    }

    public void setFours(Integer fours) {
        this.fours = fours;
    }

    public org.cricket.hawkeye.db.Ground getGround() {
        return ground;
    }

    public void setGround(org.cricket.hawkeye.db.Ground ground) {
        this.ground = ground;
    }

    public Integer getInnings() {
        return innings;
    }

    public void setInnings(Integer innings) {
        this.innings = innings;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Integer getOdi() {
        return odi;
    }

    public void setOdi(Integer odi) {
        this.odi = odi;
    }

    public org.cricket.hawkeye.db.Country getOpposition() {
        return opposition;
    }

    public void setOpposition(org.cricket.hawkeye.db.Country opposition) {
        this.opposition = opposition;
    }

    public org.cricket.hawkeye.db.Player getPlayer() {
        return player;
    }

    public void setPlayer(org.cricket.hawkeye.db.Player player) {
        this.player = player;
    }

    public Integer getPositions() {
        return positions;
    }

    public void setPositions(Integer positions) {
        this.positions = positions;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Integer getSixes() {
        return sixes;
    }

    public void setSixes(Integer sixes) {
        this.sixes = sixes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Float getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(Float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public Boolean getWasOut() {
        return wasOut;
    }

    public void setWasOut(Boolean wasOut) {
        this.wasOut = wasOut;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inning other = (Inning) obj;
        if (this.player != other.player && (this.player == null || !this.player.equals(other.player))) {
            return false;
        }
        if (this.innings != other.innings && (this.innings == null || !this.innings.equals(other.innings))) {
            return false;
        }
        if (this.startDate != other.startDate && (this.startDate == null || !this.startDate.equals(other.startDate))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.player != null ? this.player.hashCode() : 0);
        hash = 17 * hash + (this.innings != null ? this.innings.hashCode() : 0);
        hash = 17 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Inning {player = " + player.getName() + ",runs=" + runs + ",startDate=" + startDate + ",opposition=" + opposition.getName()
                + ",ground=" + ground.getName() + ",country=" + ground.getCountry() + ",mins=" + mins + ",ballFaced=" + ballFaced + ",fours=" + fours
                + ",sixes=" + sixes + ",strikeRate=" + strikeRate
                + ",positions=" + positions + ",dismissalType=" + dismissalType + ",innings=" + innings
                + ",odi=" + odi
                + ",wasOut=" + wasOut + ",batted=" + batted + ",matchType=" + matchType + "}";
    }

    @Override
    public int compareTo(Object o) {
        Inning thatInning = (Inning) o;
        int playerDiff = this.getPlayer().compareTo(thatInning.getPlayer());
        int startDateDiff = this.getStartDate().compareTo(thatInning.getStartDate());
        int inningsDiff = this.getInnings().compareTo(thatInning.getInnings());
        return playerDiff != 0
                ? playerDiff
                : startDateDiff != 0
                ? startDateDiff
                : inningsDiff;


    }

    @Override
    public boolean like(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        final Inning otherInning = (Inning) otherObject;
        Player otherPlayer = otherInning.getPlayer();
        return this.getPlayer().like(otherPlayer);

    }

    @Override
    public String getEntityOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Inning.java";
        return outputClazz;
    }
     @Override
    public String getTableOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Innings.java";
        return outputClazz;
    }
    
    @Override
    public String getFetcherOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/InningFetcher.java";
        return outputClazz;
    }
}
