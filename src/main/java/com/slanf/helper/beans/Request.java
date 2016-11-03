package com.slanf.helper.beans;

import com.slanf.annotation.ConstValue.RequestMethod;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 封装Controller请求
 */
public class Request {
    /**
     * 请求类型
     */
    private RequestMethod requestMethod;
    /**
     * 请求路径
     */
    private String requestPath;

    public Request(RequestMethod _requestMethod, String _requestPath){
        this.requestMethod = _requestMethod;
        this.requestPath = _requestPath;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
}
