package com.jcos.lc4e.core.util.annotation;

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
	public int reqIndex() default 0;

	public int resIndex() default 1;

	public int modIndex() default 2;
}
