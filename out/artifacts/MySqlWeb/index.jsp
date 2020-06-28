<%@ page import="com.mysql.bean.HeiseiRider" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fun" uri="http://ww.wuyue.com/myel/function" %>
<%@ taglib prefix="tag" uri="http://ww.wuyue.com/mytag/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: murongyunge
  Date: 2019/12/16
  Time: 下午5:16
  To change this template use File | Settings | File Templates.
--%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
<%--    <base href="<%= basePath %>"> &lt;%&ndash; 为所有超链接设置基本 basePath &ndash;%&gt;--%>
  </head>
  <body>
<%--    <table align="center" width="50%" border="1">--%>

<%--      <tr>--%>
<%--        <th>IP 地址</th>--%>
<%--        <th>访问次数</th>--%>
<%--      </tr>--%>


<%--      <%--%>
<%--        Map<String, Integer> map = new HashMap<>();--%>
<%--        map.put("192.168.10.11", 5);--%>
<%--        map.put("127.0.0.10", 18);--%>
<%--        map.put("10.10.1.10", 3);--%>
<%--        pageContext.setAttribute("map", map);--%>
<%--        Set<Map.Entry<String, Integer>> sets = map.entrySet();--%>
<%--        for (Map.Entry<String, Integer> entry: sets){--%>
<%--      %>--%>
<%--        <tr>--%>
<%--          <td align="center"><%=entry.getKey()%></td>--%>
<%--          <td align="center"><%=entry.getValue()%></td>--%>
<%--        </tr>--%>

<%--      <%--%>
<%--        }--%>
<%--      %>--%>

<%--    </table>--%>

    <br/>

    <table align="center" width="50%" border="1">

        <tr>
            <th>IP 地址</th>
            <th>访问次数</th>
        </tr>
        <%
            Map<String, Integer> ipMap = new HashMap<>();
            ipMap.put("192.168.10.11", 5);
            ipMap.put("127.0.0.10", 18);
            ipMap.put("10.10.1.10", 3);
            Set<Map.Entry<String, Integer>> ipMapSets = ipMap.entrySet();
            pageContext.setAttribute("ipMapSets", ipMapSets);
        %>
        <c:forEach items="${pageScope.ipMapSets}" var="entry">
            <tr>
                <td align="center">${entry.getKey()}</td>
                <td align="center">${entry.getValue()}</td>
            </tr>
        </c:forEach>


    </table>

    <br/>

    <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd HH:mm:ss" var="date" scope="page"/>
    <fmt:parseDate value="2020-05-20" pattern="yyyy-MM-dd"/>
    <br/>
    <fmt:formatNumber value="3.1415926" pattern="0.000" var="number" scope="page" />


    <br/>
    <tag:myTag1/>
    <tag:myTag2/>
    <br/>
    <tag:myTag3>
        ${pageScope.date}
        <br/>
        <fmt:parseNumber value="3.142" pattern="0.000" />  <%--3.142--%>
    </tag:myTag3>
    <tag:attributeTag test="${empty param.name}">
        <tag:skipTag/>
    </tag:attributeTag>

    <%
      List<Integer> lists = new ArrayList<>();
      lists.add(1);
      lists.add(2);
      lists.add(3);
      lists.add(4);
      pageContext.setAttribute("lists", lists);
    %>

    <br/>

    <c:set var="riders" value="${fn:length(pageScope.map)}" scope="page"/>
<%--    <c:remove var="rider" scope="page" />--%>
    <c:out value="${fn:length(pageScope.map)}" default="空的空的" escapeXml="true"/>

    ${fun:add(pageScope.lists)}
    ${fun:isEmail("1398009647@qq.com")}

    <c:choose>
        <c:when test="${ pageScope.riders > 3}">超载了。。</c:when>
        <c:when test="${ pageScope.riders == 3}">咸淡相合适</c:when>
        <c:when test="${ pageScope.riders > 0}">哎呀，有点危险。。</c:when>
        <c:otherwise>爆了爆了</c:otherwise>
    </c:choose>

    <br/>

    <c:url value="/AjaxServlet" var="AjaxServlet" scope="page" >  <%--/MySqlWeb/AjaxServlet--%>
        <c:param name="username" value="Drive"/>
        <c:param name="password" value="123rider"/>  <%--/MySqlWeb/AjaxServlet?username=Drive&password=123rider--%>
    </c:url>
    <br/>
    ${pageContext.request.contextPath}/AjaxServlet   <%--/MySqlWeb/AjaxServlet--%>
    <br/>
    <c:if test="true" var="click" scope="page">
        <c:out value="input" default="空的空的" escapeXml="true"/>
    </c:if>
    <a href="<c:url value="/login.jsp"/>">点击这里</a>
    ${pageScope.click}
    <br/>

<%--    <p><%=request.getAttribute("username")%></p>--%>
<%--    <p><% out.print(request.getAttribute("username")); %></p>--%>
    <a href="<%= basePath %>member/member.jsp">会员入口</a>
    <a href="<%= basePath %>admin/admin.jsp">管理员入口</a>
<%--    <%--%>
<%--      com.mysql.bean.HeiseiRider rider = new HeiseiRider();--%>
<%--      session.setAttribute("HeiseiRider", rider);  //HttpSession 域对象绑定了 JavaBean 对象--%>
<%--      session.removeAttribute("HeiseiRider");  //HttpSession 域对象移除了 JavaBean 对象--%>
<%--    %>--%>

  </body>
</html>
