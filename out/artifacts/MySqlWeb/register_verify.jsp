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

        <script type="text/javascript">

            //页面显示完成监听事件
            window.onload = function () {

                var user = document.getElementById("userId");
                user.onblur = function () { //失去焦点时事件监听
                    //Ajax 四步请求操作，获取服务器响应内容，并将内容显示在 p1 中
                    //1、获取 XMLHttpRequest 对象
                    const xmlHttp = getXMLHttpRequest();
                    //2、打开与服务器的连接
                    const url = "<%= basePath %>RegisterServlet"; //http://localhost:8888/MySqlWeb/AjaxServlet
                    xmlHttp.open("POST", url, true);
                    //设置请求头
                    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    //发送
                    xmlHttp.send("username=" + user.value + "&password=" + password.value);
                    // 发送异步请求
                    //sendPost("username=" + user.value);
                    //创建 onreadystatechange 状态改变监听事件
                    xmlHttp.onreadystatechange = function () {
                        //状态为4（服务器响应结束），并且服务器响应状态码为200（响应成功）
                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
                            //服务器响应的文本格式内容
                            const text = xmlHttp.responseText;
                            //获取 span 对象
                            const spanUser = document.getElementById("errUser");
                            if (text.includes("logined")){
                                spanUser.innerHTML = "该用户名已被注册";  //设置内容
                            } else {
                                spanUser.innerHTML = "";  //设置内容
                            }

                        }
                    }
                }

                var password = document.getElementById("passId");
                password.onblur = function () { //失去焦点时事件监听
                    //Ajax 四步请求操作，获取服务器响应内容，并将内容显示在 p1 中
                    //1、获取 XMLHttpRequest 对象
                    const xmlHttp = getXMLHttpRequest();
                    //2、打开与服务器的连接
                    const url = "<%= basePath %>RegisterServlet"; //http://localhost:8888/MySqlWeb/AjaxServlet
                    xmlHttp.open("POST", url, true);
                    //设置请求头
                    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    //发送
                    xmlHttp.send("username=" + user.value + "&password=" + password.value);
                    // 发送异步请求
                    //sendPost("username=" + user.value);
                    //创建 onreadystatechange 状态改变监听事件
                    xmlHttp.onreadystatechange = function () {
                        //状态为4（服务器响应结束），并且服务器响应状态码为200（响应成功）
                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
                            //服务器响应的文本格式内容
                            const text = xmlHttp.responseText;
                            //获取 span 对象
                            const spanUser = document.getElementById("errPass");
                            if (text.includes("wrong")){
                                spanUser.innerHTML = "密码有误，请检查";  //设置内容
                            } else {
                                spanUser.innerHTML = "";  //设置内容
                            }

                        }
                    }
                }

            }

            //创建 XMLHttpRequest 异步对象
            function getXMLHttpRequest(){
                try {
                    return new XMLHttpRequest();  //大部分浏览器支持
                } catch (e) {
                    try {
                        return new ActiveXObject("Msxml2.XMLHTTP") //IE6.0
                    } catch (e) {
                        try {
                            return new ActiveXObject("Microsoft.XMLHTTTP") //IE5.5 及以下版本
                        } catch (e) {
                            alert("未支持浏览器")
                            throw e;
                        }
                    }
                }
            }

        </script>

    </head>
    <body>
        <h6 id="h6">检测用户是否登录</h6>
        <%--如果没有实现script，那么就需要在action中设置请求地址--%>
        <form action="" method="post">
            用户名：<input type="text" name="username" id="userId"/><span id="errUser"></span><br/>
            密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="passId"/><span id="errPass">密码由6～12数字或字母组成</span><br/>
            <input type="submit" name="register" id="btnReg"/>
        </form>
    </body>
</html>
