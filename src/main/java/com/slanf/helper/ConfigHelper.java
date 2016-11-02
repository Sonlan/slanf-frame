package com.slanf.helper;

import com.slanf.config.ConfigConstant;
import com.slanf.utils.PropsUtil;

import java.util.Properties;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 加载项目配置文件
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取JDBC驱动
     * @return
     */
    public static String getJDBCDriver(){
        return PropsUtil.getProperty(ConfigConstant.JDBC_DRIVER,CONFIG_PROPS);
    }

    /**
     * 获取JDBC地址
     * @return
     */
    public static String getJDBCUrl(){
        return PropsUtil.getProperty(ConfigConstant.JDBC_URL,CONFIG_PROPS);
    }

    /**
     * 获取JDBC用户名
     * @return
     */
    public static String getJDBCUsername(){
        return PropsUtil.getProperty(ConfigConstant.JDBC_USERNAME,CONFIG_PROPS);
    }

    /**
     * 获取JDBC用户密码
     * @return
     */
    public static String getJDBCPasswd(){
        return PropsUtil.getProperty(ConfigConstant.JDBC_PASSWD,CONFIG_PROPS);
    }

    /**
     * 获取项目基础包名
     * @return
     */
    public static String getAppBasePackage(){
        return PropsUtil.getProperty(ConfigConstant.APP_BASE_PACKAGE,CONFIG_PROPS);
    }

    /**
     * 获取项目页面基础文件路径
     * @return
     */
    public static String getAppViewPath(){
        return PropsUtil.getProperty(ConfigConstant.APP_VIEW_PATH,CONFIG_PROPS);
    }

    /**
     * 获取项目静态资源（如图片，js文件）基础路径
     * @return
     */
    public static String getAppAssetPath(){
        return PropsUtil.getProperty(ConfigConstant.APP_ASSET_PATH,CONFIG_PROPS);
    }
}
