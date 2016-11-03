package com.slanf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 依赖注入标签
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    /**
     * 待注入的bean名称,否则为类名首字母小写
     * @return
     */
    /*String name() default "";*/
}
