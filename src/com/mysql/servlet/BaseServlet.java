package com.mysql.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * Servlet 基类，实现子类自定义处理方法
 */
public abstract class BaseServlet implements Servlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        init();
    }


    public void init(){

    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    /**
     * 根据客户端传递的 method 方法，调用子类自定义的处理请求的方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //设置请求的编码
        servletRequest.setCharacterEncoding("UTF-8");

        //获取请求的参数
        String methodName = servletRequest.getParameter("method");
        if (methodName == null || methodName.trim().equals("")){
            throw new RuntimeException("请指定请求方法 method");
        }

        try {
            //调用子类自定义的处理请求的方法
            Method method = this.getClass().getDeclaredMethod(methodName, ServletRequest.class, ServletResponse.class);
            method.invoke(this, servletRequest, servletResponse);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            //e.printStackTrace();
            throw new RuntimeException("请求方法 " + methodName + "(servletRequest, servletResponse) 未定义");
        }



    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }

    public String getServletName(){
        return this.servletConfig.getServletName();
    }

    public ServletContext getServletContext(){
        return this.servletConfig.getServletContext();
    }

    public String getInitParameter(String name) {
        return this.servletConfig.getInitParameter(name);
    }

    public Enumeration<String> getInitParameterNames() {
        return this.servletConfig.getInitParameterNames();
    }

}
