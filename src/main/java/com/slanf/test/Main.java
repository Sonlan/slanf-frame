package com.slanf.test;

import com.slanf.annotation.ConstValue.RequestMethod;
import com.slanf.helper.BeanHelper;
import com.slanf.helper.ControllerHelper;
import com.slanf.helper.beans.Handler;
import com.slanf.helper.loader.HelperLoader;
import com.slanf.utils.ReflectUtil;

import java.lang.reflect.Method;

/**
 * Created by Song on 2016/11/3.
 */
public class Main {
    public static void main(String [] args){
        HelperLoader.init();
        Handler handler = ControllerHelper.getHandler(RequestMethod.getMethodFromName("GET"),"/test");
        Class<?> cls = handler.getControllerClass();
        Method method = handler.getActionMethod();
        Object result = ReflectUtil.invokeMethod(BeanHelper.getBean(cls),method);
        System.out.println("end");
    }
}
