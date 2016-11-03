package com.slanf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 封装反射操作
 */
public final class ReflectUtil {
    /**
     * 根据类创建对象
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls){
        Object obj = null;
        try{
            obj =  cls.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 调用成员方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result = null;
        try{
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 为成员变量赋值
     * @param obj
     * @param field
     * @param val
     */
    public  static void setField(Object obj, Field field,Object val){
        try {
            field.set(obj,val);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
