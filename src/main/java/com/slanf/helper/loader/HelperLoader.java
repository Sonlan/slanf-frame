package com.slanf.helper.loader;

import com.slanf.helper.*;
import com.slanf.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 用于加载Helper类
 */
public final class HelperLoader {
    public static Logger logger = LoggerFactory.getLogger(HelperLoader.class);
    public static void init(){
        Class<?> [] clss = {
                BeanHelper.class,
                ClassHelper.class,
                ControllerHelper.class,
                IocHelper.class
        };

        for(Class<?> cls:clss){
            logger.info("start to initiate class "+cls);
            ClassScanner.loadClass(cls.getName(),true);
        }
    }
}
