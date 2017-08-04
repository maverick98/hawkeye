
package org.cricket.hawkeye.codegen.constant;

/**
 *
 * @author msahu
 */
public enum MatchType 
{
 TESTMATCH("Test"),ODI("ODI");//Sorry I dont deal with T20 Cricket
 private String value;
 
 MatchType(String value)
 {
     this.value=value;
 }
 public String value()
 {
     return this.value;
 }
}
