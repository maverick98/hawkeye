/**This file was generated at Sat Aug 05 12:52:59 IST 2017*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.**/package org.cricket.hawkeye.db;import java.util.Set;import java.util.TreeSet;import org.hawk.module.annotation.SubTask;import java.util.Arrays;import java.util.Comparator;import java.math.BigDecimal;import java.math.RoundingMode;import org.hawk.module.plugin.HawkPluginModule;import org.common.di.ScanMe;/**** @author msahu*/@ScanMe(true)public class GroundFetcher extends HawkPluginModule{@Override
public String getPluginName() {return "hawk-eye";}public Grounds getGrounds() {return Grounds .getInstance();}@Override
public String toString() {return this.getName();}public String getName() {return "GroundFetcher";}@Override
public boolean startUp() {throw new UnsupportedOperationException("Not supported yet.");}@Override
public boolean reset() {throw new UnsupportedOperationException("Not supported yet.");}




@SubTask(name = "sortName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Ground [] sortName (Object ... args ){if(args == null || args.length == 0){return null;}Set<Ground> input = null;input  = (Set<Ground> ) args[0];Set<Ground> tmpSet = null;if(input != null && !input.isEmpty() ){tmpSet = input;}else{tmpSet = this.getGrounds().getAll();}Ground []  tmpArray = tmpSet.toArray(new Ground[] {});Arrays.sort(tmpArray,new Comparator(){@Override
public int compare(Object o1, Object o2) {Ground thisGround = (Ground) o1;Ground thatGround = (Ground) o2;return thisGround.getName ().compareTo(thatGround.getName ());}});return tmpArray;}




@SubTask(name = "likeName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Set<Ground> likeName (Object ... args ){Set<Ground> input = null;java.lang.String  name = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Ground> ) args[0];}if ( args[1] != null){name = ( java.lang.String ) args[1];}}else if(args.length == 1){name = ( java.lang.String ) args[0];}Ground otherGround = new Ground();otherGround.setName ( name );Set<Ground> result = new TreeSet<Ground>();for(Ground ground : this.getGrounds().getAll()){if(ground .like(otherGround)){result.add(ground);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Ground>();}}return result;}

@SubTask(name = "countCountry", sequence = 1, ignoreException = false, hawkParam = "var innings")public     int countCountry (Object ... args ){Set<Ground> input = null;if ( args[0] != null){input  = (Set<Ground> ) args[0];}Set<Ground> tmpSet = null;if(input != null && !input.isEmpty() ){tmpSet = input;}else{tmpSet = this.getGrounds().getAll();}int count =0;for(Ground inning : tmpSet){count++;}return count;}





@SubTask(name = "equalName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Set<Ground> equalName (Object ... args ){Set<Ground> input = null;java.lang.String  name = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Ground> ) args[0];}if ( args[1] != null){name = ( java.lang.String ) args[1];}}else if(args.length == 1){name = ( java.lang.String ) args[0];}Set<Ground> result = new TreeSet<Ground>();for(Ground ground : this.getGrounds().getAll()){if(ground.getName ().equals(name )){result.add(ground);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Ground>();}}return result;}


@SubTask(name = "equalCountry ", sequence = 1, ignoreException = false, hawkParam = "var innings,var country ")public     Set<Ground> equalCountry (Object ... args ){Set<Ground> input = null;org.cricket.hawkeye.db.Country  country = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Ground> ) args[0];}if ( args[1] != null){country = ( org.cricket.hawkeye.db.Country ) args[1];}}else if(args.length == 1){country = ( org.cricket.hawkeye.db.Country ) args[0];}Set<Ground> result = new TreeSet<Ground>();for(Ground ground : this.getGrounds().getAll()){if(ground.getCountry ().equals(country )){result.add(ground);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Ground>();}}return result;}

}
