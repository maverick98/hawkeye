package org.cricket.hawkeye.values.player;

import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.codegen.annotation.Entity;
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import org.cricket.hawkeye.codegen.annotation.AggregateClause;
import org.cricket.hawkeye.codegen.annotation.ComparisionClause;
import org.cricket.hawkeye.string.IStringService;
import org.cricket.hawkeye.string.LevenshteinDistanceAlgorithm;
import org.cricket.hawkeye.string.StringServiceImpl;
import org.cricket.hawkeye.string.exception.StringServiceException;
import org.cricket.hawkeye.codegen.SourceVO;

/**
 *
 * @author msahu
 */
@Entity(ownLikeImpl = true)
public class Player implements SourceVO {

    private static final Logger logger = Logger.getLogger(Player.class);
    IStringService stringService = new StringServiceImpl(new LevenshteinDistanceAlgorithm());
        @HQLGenerate(
            comparision =
            @ComparisionClause(moreThan = false, lessThan = false,after = false ,before = false ),
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
    public String name = "";
        @HQLGenerate(
            comparision =
            @ComparisionClause(
                moreThan = false, 
                lessThan = false,
                after = false ,
                before = false,
                sort =false , 
                like = false,
                equal = false
                
                ),
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
    private Set<org.cricket.hawkeye.db.Inning> innings = new TreeSet<org.cricket.hawkeye.db.Inning>();

    public Set<org.cricket.hawkeye.db.Inning> getInnings() {
        return innings;
    }

    public void setInnings(Set<org.cricket.hawkeye.db.Inning> innings) {
        this.innings = innings;
    }

    public IStringService getStringService() {
        return stringService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public boolean like(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        final Player otherPlayer = (Player) otherObject;
        String otherPlayerName = otherPlayer.getName();
        int editDistance = -1;
        try {
            editDistance = this.getStringService().calculateEditDistance(this.getName(), otherPlayerName);
        } catch (StringServiceException ex) {
            logger.error("error", ex);
        }

        return editDistance < 10;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (getClass() != obj.getClass()) {
            return -1;
        }
        final Player other = (Player) obj;

        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  player = ").append(this.getName());
        sb.append(" ,no of innings = ").append(this.getInnings().size());
        return sb.toString();
    }

    @Override
    public String getEntityOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Player.java";
        return outputClazz;
    }
    @Override
    public String getTableOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Players.java";
        return outputClazz;
    }
    
    @Override
    public String getFetcherOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/PlayerFetcher.java";
        return outputClazz;
    }
}
