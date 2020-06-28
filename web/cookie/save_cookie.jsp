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
        <title>保存 Cookie</title>
    </head>
    <body>
        <h3>向浏览器保存 Cookie</h3>
        <%
            Cookie useCookie1 = new Cookie("username1", "Den-O");
            response.addCookie(useCookie1);
            Cookie useCookie2 = new Cookie("username2", "Ghost");
            response.addCookie(useCookie2);
        %>
    </body>
</html>
