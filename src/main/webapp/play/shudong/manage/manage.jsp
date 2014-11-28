<%@ page import="tool.R" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--管理树洞--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 3:01 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../../../lib/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../../lib/js/jquery.min.js"></script>
    <script src="../../../lib/js/bootstrap.min.js"></script>
    <link href="../../../lib/css/main.css" rel='stylesheet'>
    <script src="../../../lib/js/main.js"></script>
    <title>我们的树洞</title>
    <style>
        hr {
            margin: 0 0 2px 0;
        }

        a:link {
            color: GrayText;
        }
    </style>

</head>
<body>
<div class="container">
    <br>
    <%--默认拿出前changeCount通知--%>
    <jsp:include page="GetShuDongManageFormForAJAX.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="size" value="<%=R.ChangeCount%>"/>
    </jsp:include>
    <%--ajax 加载更多--%>
    <button class="form-control btn-info input-lg" onclick="ajaxMore(this)">更多</button>
    <br>
</div>
<script>
    <%=Tool.makeAJAXLoadMoreJS("GetShuDongManageFormForAJAX.jsp",null)%>
</script>
</body>
</html>
