<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2020/5/24
  Time: 下午6:06
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <script type="text/javascript">
            function _change() {
                const img = document.getElementById("imgCap");
                /*更换验证码*/
                img.src = "<%= basePath %>ImageCaptchaServlet?time=" + new Date().getTime();
            }
        </script>
    </head>
    <body>

        <h3>登&nbsp;&nbsp;&nbsp;&nbsp;录</h3>
        <form action="" method="post">
            用户名：<input type="text" name="username"/><br/>
            密    码：<input type="password" name="password"/><br/>
            验证码：<input type="text" name="imageCaptcha" size="3"/>
                   <img id="imgCap" src="<%= basePath %>ImageCaptchaServlet"/>
                   <a href="javascript:_change()">换一张</a>
                   <br/>
            <input type="submit" name="login" value="登&nbsp;&nbsp;录"/>
        </form>
    </body>
</html>
