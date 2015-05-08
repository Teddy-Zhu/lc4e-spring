package com.jcos.lc4e.core.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateToken {
	
	/**
	 * request index
	 * @return
	 */
	public int reqIndex() default 0;

	public int resIndex() default 1;

	public int modIndex() default 2;
}
