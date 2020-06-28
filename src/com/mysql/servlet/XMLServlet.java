package com.mysql.servlet;

import com.mysql.utils.VerifyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XMLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);



        String xml = "<Riders>"
                + "<Rider id='16'>"
                + "<name>Drive</name>"
                + "<age>27</age>"
                + "</Rider>"
                + "<Rider id='19'>"
                + "<name>Build</name>"
                + "<age>25</age>"
                + "</Rider>"
                + "</Riders>";

        System.out.println(xml);

        //设置响应格式与编码
        resp.setContentType("text/xml; charset=utf-8");
        //响应
        resp.getWriter().print(xml);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");


    }
}
