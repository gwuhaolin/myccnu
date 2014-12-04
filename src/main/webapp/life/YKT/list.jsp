<%--一卡通消费明细--%>
<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/22/14
  Time: 2:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<%
    int type = Integer.parseInt(request.getParameter("type"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
    <script src="../../lib/js/jquery.min.js"></script>
    <script src="../../lib/js/semantic.min.js"></script>
    <script src="../../lib/js/main.js"></script>
    <title><%=ManageYKT.tranTypeInt(type)%>
    </title>
</head>
<body>

<!--用于返回个人主页面-->
<a href="index.jsp?cmd=NO" id="homeBtn" class="ui button circular icon inverted black"
   style="position: fixed;right: 5px;bottom: 5px">
    <i class="icon user"></i>
</a>

<div class="ui stackable three column page grid">

    <%--默认拿出前changeCount通知--%>
    <jsp:include page="GetAJAXServlet.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="type" value="<%=type%> "/>
    </jsp:include>
    <%=Tool.makeAjaxLoadMoreBtnHtml()%>
</div>
<script>
    <%
    Map<String,Object> params=new HashMap<>(1);
    params.put("type",type);
    %>
    <%=Tool.makeAJAXLoadMoreJS("GetAJAXServlet.jsp",params)%>
</script>
</body>
</html>
