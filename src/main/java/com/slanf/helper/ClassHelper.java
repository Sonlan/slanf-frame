package com.slanf.helper;

import com.slanf.annotation.Controller;
import com.slanf.annotation.Service;
import com.slanf.scanner.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 类加载助手
 */
public final class ClassHelper {
    /**
     * 用于存储所加载的类
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassScanner.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下所有类
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取特定标签对应的类
     * @param annotationClass 标签类
     * @return
     */
    public static Set<Class<?>> getClassSetOfAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获得所有bean类（包括Service类以及Controller类）
     * @return
     */
    public static Set<Class<?>> getClassSetOfAnnotation(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            //TO-DO
            // 如果扩展了，需要更改
            if(cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
