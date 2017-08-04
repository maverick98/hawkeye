/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cricket.hawkeye.codegen.annotation;

/**
 *
 * @author manoranjan
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author msahu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HQLGenerate
{
  String field() default "";
  ComparisionClause comparision();
  AggregateClause aggregate();
}