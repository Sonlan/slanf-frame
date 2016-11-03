package com.slanf.beans;

import java.util.Map;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 请求的参数对象
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
