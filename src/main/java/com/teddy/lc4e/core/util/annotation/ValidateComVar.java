package com.teddy.lc4e.core.util.annotation;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by teddy on 2015/6/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateComVar {

    public String name() default "";

    public String needString() default "";

    public boolean needBoolean() default true;

    public double needDouble() default 0;

    public float needFloat() default 0;

    public int needInt() default 0;
}
