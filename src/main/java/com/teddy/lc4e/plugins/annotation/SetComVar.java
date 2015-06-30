package com.teddy.lc4e.plugins.annotation;

import com.teddy.lc4e.core.util.common.Global;

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

    String[] comVar() default {};

    int reqIndex() default 0;

    int resIndex() default 1;

    int modIndex() default 2;

    String cacheName() default Global.VAR;
}

