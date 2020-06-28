package com.mysql.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义的请求处理方法 Servlet
 */
public class CustomServlet extends BaseServlet {

    public void doUpdate(ServletRequest request, ServletResponse response) throws IOException, ServletException{
        System.out.println("******update*****");
    }

    public void doDelete(ServletRequest request, ServletResponse response) throws IOException, ServletException{
        System.out.println("******delete*****");
    }

    public void doModify(ServletRequest request, ServletResponse response) throws IOException, ServletException{
        System.out.println("******modify*****");
    }

}
