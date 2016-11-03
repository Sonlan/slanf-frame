package com.slanf.servlet;

import com.slanf.annotation.ConstValue.RequestMethod;
import com.slanf.beans.Data;
import com.slanf.beans.Param;
import com.slanf.beans.View;
import com.slanf.helper.BeanHelper;
import com.slanf.helper.ConfigHelper;
import com.slanf.helper.ControllerHelper;
import com.slanf.helper.beans.Handler;
import com.slanf.helper.loader.HelperLoader;
import com.slanf.utils.JsonUtil;
import com.slanf.utils.ReflectUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * 核心Servlet
 */
public class DispatcherServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关helper
        HelperLoader.init();
        super.init(servletConfig);
/*        //获取ServletContext，用于注册Servlet
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理jsp的Servlet
        ServletRegistration jspRegist = servletContext.getServletRegistration("jsp");
        jspRegist.addMapping(ConfigHelper.getAppViewPath()+"*");
        //注册处理静态资源
        ServletRegistration defaultRegist = servletContext.getServletRegistration("default");
        defaultRegist.addMapping(ConfigHelper.getAppAssetPath()+"*");*/
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方法与路径
        String requestMethod = request.getMethod().toUpperCase();
        String requestPath = request.getPathInfo();
        Map<String,Object> params = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            params.put(paramName,request.getParameter(paramName));
        }
        Param param = new Param(params);
        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(RequestMethod.getMethodFromName(requestMethod),requestPath);
        //调用对应处理类实例及其处理方法
        Class<?> cls = handler.getControllerClass();
        Method method = handler.getActionMethod();
        Object result = ReflectUtil.invokeMethod(BeanHelper.getBean(cls),method,param);

        //判断返回类型
        if(result instanceof View){
            View view = (View)result;
            String path = view.getPath();
            response.sendRedirect(request.getContextPath()+path);
        }else if (result instanceof Data){
            Data data = (Data)result;
            Object model = data.getModel();
            if(null != model){
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JsonUtil.toJson(model));
            }
        }
    }
}
