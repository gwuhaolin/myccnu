<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String XH=request.getParameter("XH");

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
	<title>感谢你的加入</title>
</head>
<body>
<div class="container">
	<br>
	<img src="img/rocket.png" class="center-block img-responsive">
	<br>
	<div class="alert alert-info">
		Hi,<em><%=XH%></em>,欢迎加入我们!在这里你会发现原来有像你一样古怪的小伙伴们!你会因为遇到知己而充满激情!
	</div>
	<div class="alert alert-danger">
		你需要于本星期日下午3点到<em>东二食堂大学生服务中心418室</em>
		,我们相互了解一下,如有疑问请加QQ <em>569230199</em> 咨询
	</div>
</div>
</body>
</html>