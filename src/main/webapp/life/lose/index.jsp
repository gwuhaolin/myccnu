<%@ page import="tool.Tool" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    byte type = ManageLose.TYPE_Lose;
    try {
        type = Byte.parseByte(request.getParameter("type"));
    } catch (Exception e) {
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../lib/js/jquery.min.js"></script>
    <script src="../../lib/js/semantic.min.js"></script>
    <link href="../../lib/css/main.css" rel='stylesheet'>
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <script src="../../lib/js/main.js"></script>
    <title><%=ManageLose.tranTypeToChinese(type)%>
    </title>
</head>
<body>
<div class="container">
    <br><br>
    <%--默认拿出前changeCount通知--%>
    <jsp:include page="GetMoreForAJAX.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="type" value="<%=type%>"/>
    </jsp:include>

    <%=Tool.makeAjaxLoadMoreBtnHtml()%>
    <br>
    <br>
    <br>
    <br>
</div>

<script>
    <%
    Map<String,Object> params=new HashMap<>(1);
    params.put("type",type);
    %>
    <%=Tool.makeAJAXLoadMoreJS("GetMoreForAJAX.jsp",params)%>
</script>
<%--链接--%>
<%@ include file="link.jsp" %>


</body>
</html>
