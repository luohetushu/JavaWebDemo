<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2020/5/23
  Time: 下午5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>获取 Cookie</title>
    </head>
    <body>
        <h3>获取浏览器"归还"的 Cookie</h3>
        <%
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies){
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        %>
    </body>
</html>
