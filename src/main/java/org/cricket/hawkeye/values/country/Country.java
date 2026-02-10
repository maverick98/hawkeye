package org.cricket.hawkeye.values.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.cricket.hawkeye.values.player.Player;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

import org.cricket.hawkeye.codegen.annotation.AggregateClause;
import org.cricket.hawkeye.codegen.annotation.ComparisionClause;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;
import org.cricket.hawkeye.codegen.annotation.Entity;
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
public class Country implements SourceVO {

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
    public Set<org.cricket.hawkeye.db.Ground> grounds = new HashSet<org.cricket.hawkeye.db.Ground>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Set<org.cricket.hawkeye.db.Ground> grounds) {
        this.name = name;
        this.grounds = grounds;
    }

    public IStringService getStringService() {
        return stringService;
    }

    public Set getGrounds() {
        return grounds;
    }

    public void setGrounds(Set grounds) {
        this.grounds = grounds;
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
        final Country other = (Country) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Country =").append(name);

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
        final Country other = (Country) obj;
        //System.out.println("Comparing  "+this.getOdi() + " with "+ other.getOdi());
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
        final Country otherCountry = (Country) otherObject;
        String otherCountryName = otherCountry.getName();
        int editDistance = -1;
        try {
            editDistance = this.getStringService().calculateEditDistance(this.getName(), otherCountryName);
        } catch (StringServiceException ex) {
            logger.error("error", ex);
        }

        return editDistance < 10;
    }
    @JsonIgnore    
    @Override
    public String getEntityOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Country.java";
        return outputClazz;
    }
    @JsonIgnore    
     @Override
    public String getTableOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/Countrys.java";
        return outputClazz;
    }
    @JsonIgnore    
    @Override
    public String getFetcherOutputJavaFile() {
        String outputClazz = "./src/main/java/org/cricket/hawkeye/db/CountryFetcher.java";
        return outputClazz;
    }
}
