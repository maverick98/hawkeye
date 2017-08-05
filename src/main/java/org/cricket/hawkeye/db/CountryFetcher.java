/**This file was generated at Sat Aug 05 12:53:00 IST 2017*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.**/package org.cricket.hawkeye.db;import java.util.Set;import java.util.TreeSet;import org.hawk.module.annotation.SubTask;import java.util.Arrays;import java.util.Comparator;import java.math.BigDecimal;import java.math.RoundingMode;import org.hawk.module.plugin.HawkPluginModule;import org.common.di.ScanMe;/**** @author msahu*/@ScanMe(true)public class CountryFetcher extends HawkPluginModule{@Override
public String getPluginName() {return "hawk-eye";}public Countrys getCountrys() {return Countrys .getInstance();}@Override
public String toString() {return this.getName();}public String getName() {return "CountryFetcher";}@Override
public boolean startUp() {throw new UnsupportedOperationException("Not supported yet.");}@Override
public boolean reset() {throw new UnsupportedOperationException("Not supported yet.");}




@SubTask(name = "sortName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Country [] sortName (Object ... args ){if(args == null || args.length == 0){return null;}Set<Country> input = null;input  = (Set<Country> ) args[0];Set<Country> tmpSet = null;if(input != null && !input.isEmpty() ){tmpSet = input;}else{tmpSet = this.getCountrys().getAll();}Country []  tmpArray = tmpSet.toArray(new Country[] {});Arrays.sort(tmpArray,new Comparator(){@Override
public int compare(Object o1, Object o2) {Country thisCountry = (Country) o1;Country thatCountry = (Country) o2;return thisCountry.getName ().compareTo(thatCountry.getName ());}});return tmpArray;}




@SubTask(name = "likeName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Set<Country> likeName (Object ... args ){Set<Country> input = null;java.lang.String  name = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Country> ) args[0];}if ( args[1] != null){name = ( java.lang.String ) args[1];}}else if(args.length == 1){name = ( java.lang.String ) args[0];}Country otherCountry = new Country();otherCountry.setName ( name );Set<Country> result = new TreeSet<Country>();for(Country country : this.getCountrys().getAll()){if(country .like(otherCountry)){result.add(country);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Country>();}}return result;}

@SubTask(name = "countGrounds", sequence = 1, ignoreException = false, hawkParam = "var innings")public     int countGrounds (Object ... args ){Set<Country> input = null;if ( args[0] != null){input  = (Set<Country> ) args[0];}Set<Country> tmpSet = null;if(input != null && !input.isEmpty() ){tmpSet = input;}else{tmpSet = this.getCountrys().getAll();}int count =0;for(Country inning : tmpSet){count++;}return count;}





@SubTask(name = "equalName ", sequence = 1, ignoreException = false, hawkParam = "var innings,var name ")public     Set<Country> equalName (Object ... args ){Set<Country> input = null;java.lang.String  name = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Country> ) args[0];}if ( args[1] != null){name = ( java.lang.String ) args[1];}}else if(args.length == 1){name = ( java.lang.String ) args[0];}Set<Country> result = new TreeSet<Country>();for(Country country : this.getCountrys().getAll()){if(country.getName ().equals(name )){result.add(country);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Country>();}}return result;}


@SubTask(name = "equalGrounds ", sequence = 1, ignoreException = false, hawkParam = "var innings,var grounds ")public     Set<Country> equalGrounds (Object ... args ){Set<Country> input = null;java.util.HashSet  grounds = null;if(args == null || args.length == 0){return null;}if(args.length == 2){if ( args[0] != null){input  = (Set<Country> ) args[0];}if ( args[1] != null){grounds = ( java.util.HashSet ) args[1];}}else if(args.length == 1){grounds = ( java.util.HashSet ) args[0];}Set<Country> result = new TreeSet<Country>();for(Country country : this.getCountrys().getAll()){if(country.getGrounds ().equals(grounds )){result.add(country);}}if(input != null && !input.isEmpty() ){if(input.retainAll(result)){result = input;}else{result = new TreeSet<Country>();}}return result;}

}
