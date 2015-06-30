package com.teddy.lc4e.plugins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateToken {
	
	/**
	 * request index
	 * @return
	 */
	int reqIndex() default 0;

	int resIndex() default 1;

	int modIndex() default 2;
}
