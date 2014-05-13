<%@ page import="tool.Tool" %>
<%--用学号查询CET.查询界面--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:34 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%//自动登入
	if (Tool.sentXHHMMtoLoginPage("result.jsp",response,request)){
		return;
	}else {
		response.sendRedirect("../../java/bind/login.jsp?info=You Have To Login Before Do This!");
	}
%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
	<%--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">--%>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
	<%--<link href="/lib/css/semanticUI.min.css" rel="stylesheet">--%>
	<%--<script src="/lib/js/jquery-2.0.2.min.js"></script>--%>
	<%--<script src="/lib/js/semanticUI.min.js"></script>--%>
	<%--<link href="/lib/css/main.css" rel='stylesheet'>--%>
	<%--<script src="/lib/js/main.js"></script>--%>
	<%--<title>CET QUERY</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
	<%--<img src="img/rocket.png" class="img-responsive center-block">--%>
	<%--<br>--%>
	<%--<form class="form-group" action="result.jsp" method="get_page_XH">--%>
		<%--<input type="text" maxlength="10" name="xh" class="form-control input-lg" placeholder="Tell me your student ID!" value="<%=Tool.getXHMMfromCookie(request)[0]%>">--%>
		<%--<br>--%>
		<%--<input type="submit" value="GO" class="form-control btn-info input-lg">--%>
	<%--</form>--%>
	<%--<%--%>
		<%--String info=request.getParameter("info");--%>
		<%--if(info!=null){--%>
	<%--%>--%>
	<%--<div class="alert alert-info"><%=info%></div>--%>
	<%--<%--%>
		<%--}--%>
	<%--%>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>