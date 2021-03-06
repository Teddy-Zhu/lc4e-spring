package com.teddy.lc4e.plugins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface ValidateParams {
    ValidateParam[] fields() default @ValidateParam;

    boolean useSelect() default false;

    ValidateComVar validate() default @ValidateComVar;

    ValidateParam[] trueFields() default {};

    ValidateParam[] falseFields() default {};

    int reqIndex() default 0;

    int resIndex() default 1;

    int modIndex() default 2;
}
