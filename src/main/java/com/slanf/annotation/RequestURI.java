package com.slanf.annotation;

import com.slanf.annotation.ConstValue.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 类似RequestMapping标签
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestURI {
    /**
     * 访问链接
     * @return
     */
    String URI() default "";

    /**
     * 请求方式
     * 0：GET
     * 1： POST
     * 2: PUT
     * 3: PUSH
     * 4: DELETE
     * @return
     */
    RequestMethod METHOD() default RequestMethod.NONE;
}
