<%@ page import="life.lose.ManageLose" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 7:46 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<title>捡到东西</title>
</head>
<body>
<div class="container">
	<br><br>
	<img src="img/update.png" class="img-responsive center-block">
	<form  action="AddOneServlet.jsp" method="post">
		<div class="panel panel-info">
			<div class="panel-heading"><span class="glyphicon glyphicon-bookmark"></span> 描述</div>
			<!--问题描述-->
			<textarea class="form-control btn-lg" name="des" style="height: 150px"></textarea>
			<!--失物地点-->
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-map-marker"></i></span>
				<input type="text" class="form-control" name="location" placeholder="你在什么地方捡到了该物品">
			</div>
			<!--联系方式-->
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
				<input type="text" class="form-control" name="phone" placeholder="输入你的电话">
			</div>
		</div>
		<input type="hidden" name="type" value="<%=ManageLose.TYPE_Update%>">
		<input type="submit" class="form-control btn-info input-lg">
		<br><br>
	</form>
</div>


<%--搜索--%>
<form action="searchResult.jsp" method="post" style="margin: 0;position: fixed;top: 0;z-index: 100;opacity: 0.8">
	<input type="hidden"  name="type" value="<%=ManageLose.TYPE_Lose%>">
	<div class="input-group">
		<input type="text" name="want" class="form-control" placeholder="先搜索看有没有同学掉了你捡到的东西!">
		<span class="input-group-btn"><input type="submit" class="btn btn-info" value="GO"></span>
	</div>
</form>

<%--链接--%>
<%@ include file="link.jsp"%>

</body>
</html>