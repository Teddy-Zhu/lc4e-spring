package com.teddy.lc4e.plugins.annotation;

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

    String name() default "";

    String needString() default "";

    boolean needBoolean() default true;

    double needDouble() default 0;

    float needFloat() default 0;

    int needInt() default 0;
}
