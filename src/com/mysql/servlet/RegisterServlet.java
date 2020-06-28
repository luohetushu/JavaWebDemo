package com.mysql.servlet;

import com.mysql.utils.VerifyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");

        //获取请求参数
        String name = req.getParameter("username");
        //检测用户名是否已登录
        if (name.equalsIgnoreCase("riders")){
            resp.getWriter().print("logined");
        } else {
            resp.getWriter().print("no");
        }

        //检测密码是否正确
        String password = req.getParameter("password");
        if (VerifyUtils.isPasswordRight(password)){
            resp.getWriter().print("right");
        } else {
            resp.getWriter().print("wrong");
        }

    }
}
