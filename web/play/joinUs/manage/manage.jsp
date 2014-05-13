<%@ page import="java.util.List" %>
<%@ page import="play.joinUs.JoinusEntity" %>
<%@ page import="play.joinUs.ManageJoinUs" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/2/14
  Time: 8:31 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	List<JoinusEntity> joinusEntities = ManageJoinUs.getAll();
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="/lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="/lib/js/jquery-2.0.2.min.js"></script>
	<script src="/lib/js/bootstrap.min.js"></script>
	<link href="/lib/css/main.css" rel='stylesheet'>
	<script src="/lib/js/main.js"></script>
	<title>加入我们管理</title>
	<style>
		a{
			margin: 5px;
		}
	</style>
</head>
<body>
<div class="container">
	<%
		for (JoinusEntity one : joinusEntities) {
	%>
	<%--学号--%>
	<a class="form-control btn-danger" href="/java/studentsInfo/index.jsp?XH=<%=one.getXh()%>"><%=one.getXh()%></a>
	<%
		}
	%>
</div>
</body>
</html>