package com.teddy.lc4e.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by teddy on 2015/6/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetComVar {

    public String[] comVar() default {};

    public int reqIndex() default 0;

    public int resIndex() default 1;

    public int modIndex() default 2;

    public boolean useCache() default true;

    public String cacheName() default "comVar";
}

