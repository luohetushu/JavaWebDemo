<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2020/5/20
  Time: 下午4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <title>检测用户是否登录</title>
    </head>
    <body>
        <h6 id="h6">检测用户是否登录</h6>
        <%--如果没有实现script，那么就需要在action中设置请求地址--%>
        <form action="<%= basePath %>RegisterServlet" method="post">
            用户名：<input type="text" name="username" id="userId"/><span id="errUser"></span><br/>
            密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="passId"/><span id="errPass">密码由6～12数字或字母组成</span><br/>
            <input type="submit" name="register" id="btnReg"/> <%--在浏览器中，点击 提交 会跳转进 action 设置的地址--%>
        </form>
    </body>
</html>
