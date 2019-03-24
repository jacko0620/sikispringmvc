package com.siki.servlet;

import com.siki.annotation.SikiController;
import com.siki.annotation.SikiRepository;
import com.siki.annotation.SikiService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
public class SikiDispatcherServlet extends HttpServlet {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> ioc = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("init start");

        // 1.加载配置文件，获取的是，扫描的路径
        doLoadConfig(config);

        // 2.根据配置文件，扫描路径下的所有文件，初始化它们
        String scanPackage = properties.getProperty("scanPackage"); // com.siki→com/siki
        doScan(scanPackage);
        logger.info("scan finish");

        // 3.拿到扫描的类，通过java反射机制，实例化这些类，并且装进ioc容器(Map<String,Object>)中
        doInstance();
        logger.info("instance finish");

        // 4.注入第三步拿到的类

        // 5.初始化HandlerMapping，将@SikiRequestMapping里面配置的url路径和实际的方法对应上

        // 6.注入第五步拿到的类
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            Class<?> clazz;
            for (String className:classNames) {
                clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(SikiController.class)) {
                    // 控制层
                    ioc.put(toFirstWordLower(clazz.getSimpleName()),clazz.newInstance());
                } else if (clazz.isAnnotationPresent(SikiService.class)) {
                    // 服务层
                    // impl
                    SikiService sikiService =  clazz.getAnnotation(SikiService.class);
                    String value = sikiService.value();
                    if ("".equals(value)) {
                        value = toFirstWordLower(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(value,instance);
                    // service
                    for (Class ins:clazz.getInterfaces()) {
                        ioc.put(ins.getName(),instance);
                    }
                } else if (clazz.isAnnotationPresent(SikiRepository.class)) {
                    // dao
                    SikiRepository sikiRepository = clazz.getAnnotation(SikiRepository.class);
                    // impl
                    String value = sikiRepository.value();
                    if ("".equals(value)) {
                        value = toFirstWordLower(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(value,instance);
                    // service
                    for (Class ins:clazz.getInterfaces()) {
                        ioc.put(ins.getName(),instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doScan(String scanPackage) {
        String packageName = scanPackage.replaceAll("\\.","/");
        URL url = this.getClass().getClassLoader().getResource(packageName);
        File dir = new File(url.getFile());
        String className;
        for (File file:dir.listFiles()) {
            if (file.isDirectory()) {
                // 文件夹
                doScan(scanPackage + "." + file.getName());
            } else {
                // 文件
                className = scanPackage + "." + file.getName().replace(".class",""); // .class
                classNames.add(className);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void doLoadConfig(ServletConfig config) {
        String location = config.getInitParameter("contextConfigLocation");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String toFirstWordLower(String str) {
        char[] strArray = str.toCharArray();
        strArray[0] += 32;
        return String.valueOf(strArray);
    }
}
