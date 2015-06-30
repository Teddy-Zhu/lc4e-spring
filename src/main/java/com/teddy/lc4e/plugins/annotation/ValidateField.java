package com.teddy.lc4e.plugins.annotation;

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
	int index() default -1;

	int defaultInt() default -1;

	String defaultString() default "";

	boolean defaultBoolean() default false;

	double defaultDouble() default 0.0;
	/**
	 * validate field in parameter[index]
	 */
	String fieldName() default "";

	/**
	 * regex
	 */
	String regexStr() default "";

	/**
	 * validate parameter is not null
	 */
	boolean NotNull() default false;

	/**
	 * max length of string
	 */
	int maxLen() default -1;

	/**
	 * min length of String
	 */
	int minLen() default -1;

	/**
	 * Integer parameter's max value
	 */
	int maxVal() default -1;

	/**
	 * Integer parameter's min value
	 */
	int minVal() default -1;
}
