package com.teddy.lc4e.core.util.annotation;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
	String value() default "user";

}
