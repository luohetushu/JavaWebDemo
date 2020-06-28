package com.mysql.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 查看 Servlet 生命周期
 * Servlet 中大多数方法不是由我们开发者调用，而是 tomcat 调用，同时也创建 Servlet 对象
 */
public class LifecycleServlet implements Servlet {
    /**
     * 生命周期方法
     * 在 tomcat 等服务器创建 Servlet 对象后立刻执行，且只执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init()....");


        // 获取所有的初始化参数名, web.xml 中配置
        // 在 <servlet>...</servlet> 中的 <init-param> 中，根据指定名<param-name>获取 Servlet 参数值<param-value>
        Enumeration<String> enums = servletConfig.getInitParameterNames();
        while (enums.hasMoreElements()){
            String name = enums.nextElement();
            System.out.print("参数名：" + name);
            //根据参数名获取参数值
            System.out.println("，参数值：" + servletConfig.getInitParameter(name));
        }

    }


    /**
     * 获取 Servlet 配置信息
     * javax.servlet.ServletConfig 实现子类对象包含了 Servlet 实现子类在 web.xml 中的配置信息， <servlet>...</servlet>
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig()....");
        return null;
    }

    /**
     * 生命周期方法
     * 服务器会将客户端发送的请求交给 Servlet 处理，调用该方法（每处理一次请求，调用一次该方法）
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        System.out.println("service()....");
    }

    /**
     * 获取 Servlet 信息
     * 该方法返回值我们自己设定，方法自己调用
     * @return
     */
    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo()....");
        return null;
    }

    /**
     * 生命周期方法
     * 在 tomcat 等服务器销毁 Servlet 对象之前执行，且只执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy()....");
    }

}
