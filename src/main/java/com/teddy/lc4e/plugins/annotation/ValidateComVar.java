package com.teddy.lc4e.plugins.annotation;

import com.teddy.lc4e.global.Global;

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

    String needValue() default Global.DEFAULT_NONE;
}
