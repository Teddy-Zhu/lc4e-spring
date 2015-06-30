
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
public @interface ValidateComVarGroup {

    ValidateComVar[] fields() default {};

    int reqIndex() default 0;

    int resIndex() default 1;

    int modIndex() default 2;
}
