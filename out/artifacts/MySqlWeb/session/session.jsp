<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2020/5/24
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>重写 URL</title>
    </head>
    <body>

        <%-- 重写 url--%>
        <a href="<%= basePath %>index.jsp;sessionId=<%=session.getId()%>">点击这里</a>
        <a href="<%= basePath %>index.jsp;sessionId=<%=session.getId()%>">点击这里</a>
        <a href="<%= basePath %>index.jsp;sessionId=<%=session.getId()%>">点击这里</a>

        <%
            // response.encodeUrl(String url);
            // 查看客户端 Cookie 是否存在，如果不存在或被禁用，则在 url 后添加 sessionId 请求参数
            //如果存在，则不会添加 sessionId 请求参数
            out.print(response.encodeURL(basePath + "index.jsp"));
        %>

    </body>
</html>
