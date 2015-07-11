package com.teddy.lc4e.plugins.annotation;

import com.teddy.lc4e.global.Global;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateParam {
    /**
     * parameter's index
     */
    int index() default -1;

    String defaultValue() default Global.DEFAULT_NONE;

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
    boolean required() default false;

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
