package com.siki.annotation;

import java.lang.annotation.*;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SikiRequestParam {
    public String value() default "";
}
