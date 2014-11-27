<%@ page import="play.movie.ManageMovie" %>
<%@ page import="tool.Tool" %>
<%--电影列表--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-2-3
  Time: 下午1:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String target = request.getParameter("target");
    if (target == null) {
        response.sendRedirect("index.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../lib/js/jquery.min.js"></script>
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <script src="../../lib/js/semantic.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
    <script src="../../lib/js/main.js"></script>
    <title><%=ManageMovie.targetToChineseString(target)%>
    </title>
</head>
<body>
<div class="ui four column stackable page grid">
    <%--默认拿出前changeCount电影--%>
    <jsp:include page="GetMovieForAJAX.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="target" value="<%=target%>"/>
    </jsp:include>
    <%--ajax 加载更多--%>
    <button class="ui button fluid circular" onclick="ajaxMore(this)" begin="0" style="margin-top: 10px">更多</button>
    <div class="ui divider horizontal icon inverted"><a href="iWantMovie.jsp"><i class="icon heart"></i></a></div>
</div>

<%--分享链接--%>
<a href="iWantMovie.jsp" style="position: fixed;bottom: 5px;left: 5px" class="ui button red inverted icon circular"><i
        class="icon heart"></i></a>

<script>
    <%=Tool.makeAJAXLoadMoreJS("GetMovieForAJAX.jsp",",target:'"+target+"'")%>
</script>
</body>
</html>
