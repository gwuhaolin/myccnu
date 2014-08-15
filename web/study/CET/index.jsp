<%--用Cookies里的学号查询,去数据库里查询缓存去学校抓取--%>
<%@ page import="study.CET.Cet46Entity" %>
<%@ page import="study.CET.ManageCET" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String XHMM[] = Tool.getXHMMfromCookie(request);
	Cet46Entity cet;
	if (Tool.XHMMisOK(XHMM)) {
		cet = ManageCET.get(XHMM[0]);
	} else {//去绑定
		Tool.jspWriteJSForHTML_shouldBind(response, "");
		return;
	}
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
	<title>查询结果</title>
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
	<div class="alert alert-danger">Hi,<em><%=cet.getName()%>
	</em>.<%=cet.sayToYou()%>
	</div>
	<div class="alert alert-info">没有猜对你想要的?<a href="queryByIdNumber_school.jsp">点这里</a>
	</div>
	<%
	} else {//查询不到该学号的成绩或查询失败
	%>
	<img src="img/caution.png" class="img-responsive center-block">
	<br>

	<div class="alert alert-danger">很抱歉,没获得到你的成绩<a href="queryByKH_nation.jsp">再试试这里</a></div>
	<%
		}
	%>
</div>
</body>
</html>