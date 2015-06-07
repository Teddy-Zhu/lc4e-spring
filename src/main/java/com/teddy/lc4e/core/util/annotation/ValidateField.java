package com.teddy.lc4e.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateField {
	/**
	 * parameter's index
	 */
	public int index() default -1;

	public int defaultInt() default -1;

	public String defaultString() default "";

	/**
	 * validate field in parameter[index]
	 */
	public String fieldName() default "";

	/**
	 * regex
	 */
	public String regStr() default "";

	/**
	 * validate parameter is not null
	 */
	public boolean NotNull() default false;

	/**
	 * max length of string
	 */
	public int maxLen() default -1;

	/**
	 * min length of String
	 */
	public int minLen() default -1;

	/**
	 * Integer parameter's max value
	 */
	public int maxVal() default -1;

	/**
	 * Integer parameter's min value
	 */
	public int minVal() default -1;
}
