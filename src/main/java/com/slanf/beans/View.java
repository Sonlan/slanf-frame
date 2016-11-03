package com.slanf.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 返回的视图对象
 */
public class View {

    /**
     * 视图路径
     */
    private String path;
    /**
     * 模型数据
     */
    private Map<String,Object> model = new HashMap<String, Object>();

    public View(String path) {
        this.path = path;
    }

    public View addModel(String key,Object value){
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
