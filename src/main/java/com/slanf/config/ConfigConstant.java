package com.slanf.config;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 定义相关配置常量
 */
public interface ConfigConstant {
    //配置文件名
    String CONFIG_FILE = "slanf.properties";
    //JDBC配置
    String JDBC_DRIVER = "slanf.framework.jdbc.driver";
    String JDBC_URL = "slanf.framework.jdbc.url";
    String JDBC_USERNAME = "slanf.framework.jdbc.username";
    String JDBC_PASSWD = "slanf.framework.jdbc.password";

    //项目参数配置
    String APP_BASE_PACKAGE = "slanf.framework.app.base_package";
    String APP_VIEW_PATH = "slanf.framework.app.view_path";
    String APP_ASSET_PATH = "slanf.framework.app.asset_path";
}
