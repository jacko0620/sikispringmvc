package com.siki.annotation;

import java.lang.annotation.*;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SikiService {
    public String value() default "";
}
