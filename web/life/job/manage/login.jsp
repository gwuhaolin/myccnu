<%@ page import="life.jobs.ManageJob" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-2-5
  Time: 上午11:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
	<title>登入通知管理系统</title>
</head>
<body>
<div class="container">

	<%
		String info=request.getParameter("info");
		if (info!=null){
	%>
	<div class="alert alert-danger"><%=info%></div>
	<%
		}
	%>

	<form class="form-group" action="LoginServlet.jsp" method="post">
		<div class="input-group">
			<span class="input-group-addon">密码</span>
			<input type="password" name="password" class="form-control">
		</div>
		<div class="radio">
			<label>
				<input type="radio" name="target" value="<%=ManageJob.TARGET_PartTimeJob%>" checked="checked" >
				兼职
			</label>
		</div>
		<div class="radio">
			<label>
				<input type="radio" name="target" value="<%=ManageJob.TARGET_PrivateTeacher%>">
				家教
			</label>
		</div>
		<button type="submit" class="form-control btn-primary">登 入</button>
	</form>

</div>
</body>
</html>