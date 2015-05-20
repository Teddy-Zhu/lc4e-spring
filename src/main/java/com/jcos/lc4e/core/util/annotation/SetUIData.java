package com.jcos.lc4e.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by teddy on 2015/5/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetUIData {
    public String[] funcName() default {};

    public String[] varName() default {};

    public int[] useVarIndex() default {};

    public int reqIndex() default 0;

    public int resIndex() default 1;

    public int modIndex() default 2;
}
