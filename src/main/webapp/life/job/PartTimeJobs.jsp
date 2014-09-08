<%@ page import="life.jobs.ManageJob" %>
<%@ page import="tool.Tool" %>
<%--通知列表--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 3:01 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<script src="http://cdn.bootcss.com/jquery/2.1.1-rc2/jquery.min.js"></script>
<link href="http://cdn.bootcss.com/semantic-ui/0.16.1/css/semantic.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/semantic-ui/0.16.1/javascript/semantic.min.js"></script>

	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
	<script src="../../lib/js/main.js"></script>
	<title>最新兼职信息</title>

</head>
<body>
<div class="ui stackable three column page grid">

	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetAJAXServlet.jsp">
		<jsp:param name="target" value="<%=ManageJob.TARGET_PartTimeJob%>"/>
		<jsp:param name="begin" value="0"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="ui button fluid circular" onclick="ajaxMore(this)" begin="0" style="margin-top: 10px">更多
	</button>
	<div class="ui divider horizontal icon inverted"><i class="icon smile"></i></div>

	<div class="column">
		<div class="ui divided selection list inverted">
			<%--咨询电话--%>
			<div class="item">
				<i class="phone icon circular inverted black"></i>

				<div class="content">
					<a class="header">67868534</a>

					<div class="description">质询电话
					</div>
				</div>
			</div>

			<%--咨询地点--%>
			<div class="item">
				<i class="location icon circular inverted teal"></i>

				<div class="content">
					<a class="header">老图书馆三楼学生事务大厅</a>

					<div class="description">咨询地点
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<script>
	<%=Tool.makeAJAXLoadMoreJS("GetAJAXServlet.jsp",",target:'"+ManageJob.TARGET_PartTimeJob+"'")%>
</script>
<jsp:include page="searchBox.jsp"/>

</body>
</html>