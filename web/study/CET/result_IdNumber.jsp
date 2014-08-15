<%--用身份证查询CET.查询结果界面,由于没有该同学的信息就直接去学校的网站抓取--%>
<%@ page import="study.CET.Cet46Entity" %>
<%@ page import="study.CET.ManageCET" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<%
	String id = request.getParameter("id");
	String grade = request.getParameter("grade");
	if (id == null || id.length() != 18) {
		response.sendRedirect("queryByIdNumber_school.jsp?info=Your ID-Card-Number have Problem!");
		return;
	}
	Cet46Entity cet = ManageCET.scan(grade, id);
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
		if (cet.pass()) {//通过考试
	%>
	<img src="img/heart.png" class="img-responsive center-block">
	<br>

	<div class="center-block">
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-info active">总分</a>
			<a class="btn btn-success active"><%=cet.getSumScore()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">听力</a>
			<a class="btn btn-success active"><%=cet.getListening()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">阅读</a>
			<a class="btn btn-success active"><%=cet.getReading()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-info active">写作</a>
			<a class="btn btn-success active"><%=cet.getEssay()%>
			</a>
		</div>
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-info active">排名</a>
			<a class="btn btn-warning active"><%=cet.rank()%>
			</a>
		</div>
	</div>
	<%
	} else {//没有通过考试
	%>
	<img src="img/x.png" class="img-responsive center-block">
	<br>

	<div class="center-block">
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-danger active">总分</a>
			<a class="btn btn-warning active"><%=cet.getSumScore()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">听力</a>
			<a class="btn btn-warning active"><%=cet.getListening()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">阅读</a>
			<a class="btn btn-warning active"><%=cet.getReading()%>
			</a>
		</div>
		<div class="btn-group btn-group-sm btn-group-justified">
			<a class="btn btn-danger active">写作</a>
			<a class="btn btn-warning active"><%=cet.getEssay()%>
			</a>
		</div>
		<div class="btn-group btn-group-lg btn-group-justified">
			<a class="btn btn-danger active">排名</a>
			<a class="btn btn-info active"><%=cet.rank()%>
			</a>
		</div>
	</div>
	<%
		}
	%>
	<div class="alert alert-danger">Hi,<%=cet.sayToYou()%>
	</div>
	<%
	} else {//查询不到该学号的成绩或查询失败
	%>
	<img src="img/caution.png" class="img-responsive center-block">
	<br>

	<div class="alert alert-danger">很抱歉,找不到你的成绩<a href="http://www.chsi.com.cn/cet/">再试试这里,直接从全国官网查询</a></div>

	<%
		}
	%>
</div>
</body>
</html>