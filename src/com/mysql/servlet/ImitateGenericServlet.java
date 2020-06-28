package com.mysql.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 模拟 javax.servlet.GenericServlet 类
 */
public abstract class ImitateGenericServlet implements Servlet {

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

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

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
