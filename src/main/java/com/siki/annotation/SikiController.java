package com.siki.annotation;

import java.lang.annotation.*;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SikiController {
    public String value() default "";
}
