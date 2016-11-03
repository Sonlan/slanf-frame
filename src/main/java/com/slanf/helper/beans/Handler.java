package com.slanf.helper.beans;

import java.lang.reflect.Method;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 封装Controller处理对象
 */
public class Handler {
    /**
     * 被@Controller标签注解的类
     */
    private Class<?> controllerClass;
    /**
     * 被@RequestURI标签注解的方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
