package com.siki.annotation;

import java.lang.annotation.*;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SikiRepository {
    public String value() default "";
}
