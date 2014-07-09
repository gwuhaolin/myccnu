<%@ page import="tool.ccnu.CCNUPortal" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/2/14
  Time: 10:37 AM
  绑定结果
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String XH = request.getParameter("XH");
	String MM = request.getParameter("MM");
	if (CCNUPortal.XHMMisTrue(XH, MM)) {//密码正确
		Tool.setXHMMtoCookies(response, XH, MM);//保存帐号密码到cookies
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../../lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="../../lib/js/jquery.min.js"></script>
	<script src="../../lib/js/bootstrap.min.js"></script>
	<link href="../../lib/css/main.css" rel='stylesheet'>
	<script src="../../lib/js/main.js"></script>
	<title></title>

</head>
<body>
<div class="container">
	<div class="container">
		<br>
		<img src="img/heart.png" class="img-responsive center-block">
		<br>

		<div class="alert alert-success">恭喜!你已经成功绑定帐号,你会发现MyCCNU变得更加便捷了~同时我们非常感谢你的信任和支持,我们会因为你而做的更好!<br>现在重新去试试你刚才想要用的功能吧!
		</div>
		<br>
	</div>
</div>
</body>
</html>

<%
	} else {//密码错误
		response.sendRedirect("login.jsp?info=Password-not-true!");
	}
%>
