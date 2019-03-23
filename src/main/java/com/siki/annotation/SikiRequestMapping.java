package com.siki.annotation;

import java.lang.annotation.*;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SikiRequestMapping {
    public String value() default "";
}
