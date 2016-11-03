package com.slanf.annotation.ConstValue;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 */
public enum RequestMethod {
    NONE,GET,POST,PUT,PUSH,DELETE;

    public static RequestMethod getMethodFromName(String name){
        if("GET".equals(name)) return GET;
        if("POST".equals(name)) return POST;
        if("PUT".equals(name)) return PUT;
        if("PUSH".equals(name)) return PUSH;
        if("DELETE".equals(name)) return DELETE;
        return NONE;
    }
}
