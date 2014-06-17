<%--用学号查询CET.查询结果界面--%>
<%@ page import="study.CET.Cet46Entity" %>
<%@ page import="study.CET.ManageCET" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String xh = request.getParameter("XH");
	if (xh == null || xh.length() != 10) {
		response.sendRedirect("index.jsp?info=Your StudentID has Problem!");return;
	}
	Cet46Entity cet = ManageCET.get(xh);
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
	<title>Query Result</title>
</head>
<body>
<div class="container">
	<%
		if (cet != null) {//查询成功
	%>
	<%
		if (cet.isPass()) {//通过考试
	%>
	<img src="img/heart.png" class="img-responsive center-block">
	<br>
	<div class="center-block">
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-info active">Total</a>
			<a class="btn btn-success active"><%=cet.getSumScore()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">Listening</a>
			<a class="btn btn-success active"><%=cet.getListening()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">Reading</a>
			<a class="btn btn-success active"><%=cet.getReading()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">Composite</a>
			<a class="btn btn-success active"><%=cet.getCompre()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">Essay</a>
			<a class="btn btn-success active"><%=cet.getEssay()%></a>
		</div>
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-info active">Rank</a>
			<a class="btn btn-warning active"><%=cet.getRank()%></a>
		</div>
	</div>
	<%
	} else {//没有通过考试
	%>
	<img src="img/x.png" class="img-responsive center-block">
	<br>
	<div class="center-block">
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-danger active">Total</a>
			<a class="btn btn-warning active"><%=cet.getSumScore()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">Listening</a>
			<a class="btn btn-warning active"><%=cet.getListening()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">Reading</a>
			<a class="btn btn-warning active"><%=cet.getReading()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">Composite</a>
			<a class="btn btn-warning active"><%=cet.getCompre()%></a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">Essay</a>
			<a class="btn btn-warning active"><%=cet.getEssay()%></a>
		</div>
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-danger active">Rank</a>
			<a class="btn btn-info active"><%=cet.getRank()%></a>
		</div>
	</div>
	<%
		}
	%>
	<div class="alert alert-danger">Hi,<em><%=cet.getName()%></em>.<%=cet.sayToYou()%></div>
	<%
	} else {//查询不到该学号的成绩或查询失败
	%>
	<img src="img/caution.png" class="img-responsive center-block">
	<br>

	<div class="alert alert-danger">Sorry! I can't find you CET score,you can <a href="queryByIDNAME.jsp"><em>try this</em></a></div>
	<%
		}
	%>
	<em class="text-center center-block">myccnu is so easy</em>
</div>
</body>
</html>