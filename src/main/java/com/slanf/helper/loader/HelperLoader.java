package com.slanf.helper.loader;

import com.slanf.helper.*;
import com.slanf.scanner.ClassScanner;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 用于加载Helper类
 */
public final class HelperLoader {
    public static void init(){
        Class<?> [] clss = {
                ConfigHelper.class,
                BeanHelper.class,
                ClassHelper.class,
                ControllerHelper.class,
                IocHelper.class
        };

        for(Class<?> cls:clss){
            ClassScanner.loadClass(cls.getName(),false);
        }
    }
}
