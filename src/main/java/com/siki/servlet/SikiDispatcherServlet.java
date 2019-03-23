package com.siki.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
public class SikiDispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init start");
        // 1.加载配置文件，获取的是，扫描的路径

        // 2.根据配置文件，扫描路径下的所有文件，初始化它们

        // 3.拿到扫描的类，通过java反射机制，实例化这些类，并且装进ioc容器(Map<String,Object>)中

        // 4.注入第三步拿到的类

        // 5.初始化HandlerMapping，将@SikiRequestMapping里面配置的url路径和实际的方法对应上

        // 6.注入第五步拿到的类
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
