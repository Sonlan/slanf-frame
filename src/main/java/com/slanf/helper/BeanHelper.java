package com.slanf.helper;

import com.slanf.utils.ReflectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * Bean管理类
 */
public final class BeanHelper {
    /**
     * 存储Bean实例对象的Map
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();
    //TO-DO
    //此处为单例模式，之后改成Bean池或者采用clone方式
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getClassSetOfAnnotation();
        for(Class<?> cls:beanClassSet){
            BEAN_MAP.put(cls, ReflectUtil.newInstance(cls));
        }
    }

    /**
     * 获取BeanMap
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }
    /**
     * 获取Bean实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T>T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)) throw new RuntimeException("Cannot find bean of class "+cls);
        return (T)BEAN_MAP.get(cls);
    }
}
