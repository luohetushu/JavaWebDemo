<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2020/5/20
  Time: 下午1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <title>使用 window.onload() 测试</title>
        <style type="text/css">
            #bg{
                width:120px;
                height:120px;
                border:4px solid blue;
            }
        </style>
        <script type="text/javascript">
            window.onload=function(){
                document.getElementById("bg").style.backgroundColor="#F00";

                //获取按钮对象
                var button = document.getElementById("btn");
                button.onclick = function () {
                    //Ajax 四步请求操作，获取服务器响应内容，并将内容显示在 p1 中
                    //1、获取 XMLHttpRequest 对象
                    var xmlHttp = getXMLHttpRequest();
                    //2、打开与服务器的连接
                    var url = "<%= basePath %>XMLServlet"; //http://localhost:8888/MySqlWeb/AjaxServlet
                    xmlHttp.open("GET", url, true); // GET 方式请求
                    //3、发送请求 如果是 GET 方式，则设置为 null，  // 传 null 是防止部分服务器无法发送
                    xmlHttp.send(null);
                    //创建 onreadystatechange 状态改变监听事件
                    xmlHttp.onreadystatechange = function () {
                        //状态为4（服务器响应结束），并且服务器响应状态码为200（响应成功）
                        if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
                            //获取段落对象
                            const passage = document.getElementById("p1");
                            var content = "Kamen Riders:  <br/>  Heisei Riders:  <br/>";
                            //服务器响应的 xml 格式内容
                            var doc = xmlHttp.responseXML;
                            var eleArr = doc.getElementsByTagName("Rider");
                            for (let i = 0; i < eleArr.length; i++) {
                                var ele = eleArr[i];
                                var name;
                                var age;
                                if (window.addEventListener){  //处理浏览器差异 其他浏览器
                                    name = ele.getElementsByTagName("name")[0].textContent;
                                    age = ele.getElementsByTagName("age")[0].textContent;
                                } else {  //IE 浏览器
                                    name = ele.getElementsByTagName("name")[0].text;
                                    age = ele.getElementsByTagName("age")[0].text;
                                }
                                content += "Kamen Rider " + name + " is " + age + " years old.  <br/>  ";
                            }
                            passage.innerHTML = content;
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
        <div id="bg"></div>
        <p id="p1">解析 xml 内容！！！</p>
        <button id="btn">点击</button><br/>
        <select name="province_select" id="proSelId">  //监听事件，onchange
            <option>===请选择省份===</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <select name="city_select" id="citySelId">
            <option>===请选择城市===</option>
        </select>
    </body>
</html>
