package org.cricket.hawkeye.values.ground;

import org.cricket.hawkeye.values.player.Player;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.cricket.hawkeye.codegen.annotation.AggregateClause;
import org.cricket.hawkeye.codegen.annotation.ComparisionClause;
import org.cricket.hawkeye.codegen.annotation.Entity;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.string.IStringService;
import org.cricket.hawkeye.string.LevenshteinDistanceAlgorithm;
import org.cricket.hawkeye.string.StringServiceImpl;
import org.cricket.hawkeye.string.exception.StringServiceException;
import org.cricket.hawkeye.values.country.Country;
import org.cricket.hawkeye.codegen.SourceVO;

/**
 *
 * @author msahu
 */
@Entity(ownLikeImpl = true)
public class Ground implements SourceVO {

    private static final Logger logger = Logger.getLogger(Player.class);
    @JsonIgnore    
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
            @ComparisionClause(moreThan = false, lessThan = false,after = false ,before = false,sort =false , like = false),
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
    public org.cricket.hawkeye.db.Country country = new org.cricket.hawkeye.db.Country();

    public Ground() {
        super();
    }

    public Ground(String ground) {
        this.name = ground;
    }

    public Ground(String name, org.cricket.hawkeye.db.Country country) {
        this.name = name;
        this.country = country;
    }

    public IStringService getStringService() {
        return stringService;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(org.cricket.hawkeye.db.Country country) {
        this.country = country;
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
        final Ground other = (Ground) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        if (this.country != other.country && (this.country == null || !this.country.equals(other.country))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 23 * hash + (this.country != null ? this.country.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(name);
        return sb.toString();
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (getClass() != obj.getClass()) {
            return -1;
        }
        final Ground other = (Ground) obj;

        return this.getName().compareTo(other.getName());
    }

    @Override
    public boolean like(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        final Ground otherGround = (Ground) otherObject;
        String otherGroundName = otherGround.getName();
        int editDistance = -1;
        try {
            editDistance = this.getStringService().calculateEditDistance(this.getName(), otherGroundName);
        } catch (StringServiceException ex) {
            logger.error("error", ex);
        }

        return editDistance < 5;
    }
    @JsonIgnore    
    @Override
    public String getEntityOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Ground.java";
        return outputClazz;
    }
    @JsonIgnore    
     @Override
    public String getTableOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Grounds.java";
        return outputClazz;
    }
    @JsonIgnore    
    @Override
    public String getFetcherOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/GroundFetcher.java";
        return outputClazz;
    }
}
