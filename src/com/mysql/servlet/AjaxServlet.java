package com.mysql.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);  // 不能调用 否则出现 HTTP 405-Method Not ALLOWED
        //设置真实文件路径 todo 不是真的获取指定文件的路径，而是当有文件保存时，可以用来创建的路径
        // String path = this.getServletContext().getRealPath("index.jsp");
        // /Users/murongyunge/Desktop/IntelliJ/MySqlDemo/out/artifacts/MySqlWeb/index.jsp
        String path = this.getServletContext().getRealPath("ajax_utils.js");
        // /Users/murongyunge/Desktop/IntelliJ/MySqlDemo/out/artifacts/MySqlWeb/ajax_utils.js
        // String path = this.getServletContext().getRealPath("ajax-lib/ajax_utils.js");
        // /Users/murongyunge/Desktop/IntelliJ/MySqlDemo/out/artifacts/MySqlWeb/ajax-lib/ajax_utils.js
        System.out.println(path);
        //获取该文件夹下的所有文件与文件夹（子文件夹中的文件不获取）
        //Set<String> resources = this.getServletContext().getResourcePaths("/WEB-INF");
        //或取文件输入流
        //InputStream is = this.getServletContext().getResourceAsStream("index.jsp");

        //req.getRemoteAddr();  //获取客户端 ip 地址

        ServletContext servletContext = this.getServletContext();
        //同一项目中各个资源访问的累计
        Integer count = (Integer) servletContext.getAttribute("count");
        if (count == null){
            count = 1;
        } else {
            count++;
        }

        resp.setContentType("text/html; charset=utf-8");
        //服务器端响应内容
        resp.getWriter().print("<p>本页面一共被访问 " + count + " 次！</p>");

        // 保存访问量
        servletContext.setAttribute("count", count);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");

        //获取请求参数
        String name = req.getParameter("username");
        System.out.println("Hello Ajax!!!");
        //服务器端响应内容 在当前页面显示
        resp.getWriter().print("是" + name + "在发送请求");

    }
}
