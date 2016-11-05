package com.slanf.scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Song on 2016/11/2.
 * @since v0.0
 * 类搜索器，加载基础包名下的类
 */
public final class ClassScanner {
    private static Logger logger = LoggerFactory.getLogger(ClassScanner.class);
    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        //return ClassLoader.getSystemClassLoader();
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className 类名
     * @param isInitialized 是否初始化的标志
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
            //cls = Class.forName(className);
        }catch (ClassNotFoundException e){
            System.err.println("load class "+className+" falied");
            throw  new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定包下的所有类的集合
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        logger.info("ClassScanner start to scan all the classes of package "+packageName);
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));

            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(null != url){
                    String protocol = url.getProtocol();
                    if("file".equals(protocol)){
                        String packagePath = url.getPath().replace("%20"," "); //避免中文字符编码方式下空格乱码
                        addClass(classSet,packagePath,packageName);
                    }else if("jar".equals(protocol)){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if(null != jarURLConnection){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(null != jarFile){
                                Enumeration<JarEntry> jarFileEntries = jarFile.entries();
                                while (jarFileEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarFileEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            System.err.println("get class set failed");
            throw new RuntimeException(e);
        }
        return classSet;
    }

    /**
     * 按包路径以及包名添加类到集合中
     * @param classSet
     * @param packagePath 包路径
     * @param packageName 包名
     */
    private static  void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        //获得指定路径下所有。class文件及文件夹
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for(File file:files){
            String fileName = file.getName();
            //.class file
            if(file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(null != packageName && !"".equals(packageName)){
                    className = packageName+"."+className;
                }
                doAddClass(classSet,className);
            }else {//文件夹
                String subPackagePath = fileName;
                if(null != subPackagePath && !"".equals(subPackagePath)){
                    subPackagePath = packagePath+"/"+subPackagePath;
                }
                String subPackageName = fileName;
                if(null != packageName && !"".equals(packageName)){
                    subPackageName = packageName+"."+subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName); //递归查找子目录
            }
        }
    }
    /**
     * 添加指定类到集合中
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet,String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }

}
