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
        <script type="text/javascript" src="<%= basePath %>ajax-lib/ajax_utils.js">/*引用外部 js 文件*/</script>
        <script type="text/javascript">
            window.onload=function(){
                document.getElementById("bg").style.backgroundColor="#F00";
                const passage = document.getElementById("p1");
                passage.innerHTML = "<%= basePath %>ajax-lib/ajax_utils.js";
                //json
                //var json = {"name": "Drive", "job": "Kamen Rider", "age": 27, "friends":["Mach", "Chase"]};
                // json string 类型，需转义
                //var strJson = "{\"name\": \"Drive\", \"job\": \"Kamen Rider\", \"age\": 27, \"friends\":[\"Mach\", \"Chase\"]}";
                //var json1 = eval("(" + strJson + ")");  //字符串转 json
                //获取按钮对象
                var button = document.getElementById("btn");
                button.onclick = function () {
                    ajaxOption(
                        {
                            method: "GET",
                            url: "<%= basePath %>JsonServlet",
                            asyn: true,
                            param: null,
                            type: "json",
                            callBack: function (json) {
                                //获取段落对象
                                const passage = document.getElementById("p1");
                                var arr = json.friends;
                                var content = json.job + " " + json.name + " is " + json.age
                                    + " years old. His friends are " + json.job + " " + arr[0] + " and " + arr[1] + ".";
                                passage.innerHTML = content;
                            }
                        }

                    );
                    <%--ajaxParam(--%>
                    <%--    "GET",--%>
                    <%--    "<%= basePath %>JsonServlet",--%>
                    <%--    true,--%>
                    <%--    null,--%>
                    <%--    "json",--%>
                    <%--    function (json) {--%>
                    <%--        //获取段落对象--%>
                    <%--        const passage = document.getElementById("p1");--%>
                    <%--        var arr = json.friends;--%>
                    <%--        var content = json.job + " " + json.name + " is " + json.age--%>
                    <%--            + " years old. His friends are " + json.job + " " + arr[0] + " and " + arr[1] + ".";--%>
                    <%--        passage.innerHTML = content;--%>
                    <%--    }--%>
                    <%--);--%>
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
