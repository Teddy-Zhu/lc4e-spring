package com.teddy.lc4e.core.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateGroup {
    public ValidateField[] fields() default @ValidateField;

    public boolean useSelect() default false;

    public ValidateComVar validate() default  @ValidateComVar ;

    public ValidateField[] trueFields() default {};

    public ValidateField[] falseFields() default {};

    public int reqIndex() default 0;

    public int resIndex() default 1;

    public int modIndex() default 2;
}
