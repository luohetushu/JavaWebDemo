package com.mysql.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);



        String strJson = "{\"name\": \"Drive\", \"job\": \"Kamen Rider\", \"age\": 27, \"friends\":[\"Mach\", \"Chase\"]}";

        System.out.println(strJson);

        //设置响应格式与编码
        resp.setContentType("text/text; charset=utf-8");
        //响应
        resp.getWriter().print(strJson);

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
