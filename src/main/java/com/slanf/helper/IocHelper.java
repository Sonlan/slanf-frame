package com.slanf.helper;

import com.slanf.annotation.Inject;
import com.slanf.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * Ioc功能类
 */
public final class IocHelper {
    private static  Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
    static {
        //遍历所有Bean类的Field,查找Inject标签
        //为对应Filed注入初始Bean值
        for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
            Class<?> cls = beanEntry.getKey();
            doInject(cls);
        }
    }

    /**
     * 为类cls完成Ioc注入
     * @param cls
     */
    private static void doInject(Class<?> cls){
        Field [] fields= cls.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(Inject.class)){
                field.setAccessible(true);
                ReflectUtil.setField(beanMap.get(cls),field,beanMap.get(field.getType()));
            }
        }
    }
}
