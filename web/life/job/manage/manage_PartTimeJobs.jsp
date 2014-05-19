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
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="/lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="/lib/js/jquery-2.0.2.min.js"></script>
	<script src="/lib/js/bootstrap.min.js"></script>
	<link href="/lib/css/main.css" rel='stylesheet'>
	<script src="/lib/js/main.js"></script>
	<title>管理兼职信息</title>


</head>
<body>
<div class="container">
	<%--添加--%>
	<div class="panel panel-info">
		<%--修改表单--%>
		<form action="ManageServlet.jsp" method="post" class="form-group">
			<%--id--%>
			<input type="hidden" name="id" value="-1">
			<%--cmd--%>
			<input type="hidden" name="cmd" value="<%=ManageJob.CMD_Add%>">
			<%--target--%>
			<input type="hidden" name="target" value="<%=ManageJob.TARGET_PartTimeJob%>">

			<%--名称--%>
			<div class="input-group">
				<span class="input-group-addon">名称</span>
				<input type="text" name="name" class="form-control">
			</div>


			<%--简介--%>
			<div class="input-group">
				<span class="input-group-addon">简介</span>
				<input type="text" name="jobInfo" class="form-control">
			</div>


			<%--时间--%>
			<div class="input-group">
				<span class="input-group-addon">时间</span>
				<input type="text" name="jobTime" class="form-control">
			</div>

			<%--地点--%>
			<div class="input-group">
				<span class="input-group-addon">地点</span>
				<input type="text" name="jobLocation" class="form-control">
			</div>


			<%--报酬--%>
			<div class="input-group">
				<span class="input-group-addon">报酬</span>
				<input type="text" name="money" class="form-control">
			</div>

			<%--来自--%>
			<div class="input-group">
				<span class="input-group-addon">来自</span>
				<input type="text" name="manager" class="form-control">
			</div>

			<%--其他--%>
			<div class="input-group">
				<span class="input-group-addon">其他</span>
				<input type="text" name="otherInfo" class="form-control">
			</div>
			<input type="submit" class="form-control btn-primary" value="添加">

		</form>
	</div>

	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetJobsManageFormForAJAX.jsp">
		<jsp:param name="target" value="<%=ManageJob.TARGET_PartTimeJob%>"/>
		<jsp:param name="begin" value="0"/>
		<jsp:param name="size" value="<%=ManageJob.R.ChangeCount%>"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="form-control btn-info input-lg" onclick="ajaxMore(this)" begin="0">更多</button>
	<br>
</div>
<script>
	<%=Tool.makeAJAXLoadMoreJS(ManageJob.R.ChangeCount,"GetJobsManageFormForAJAX.jsp",",target:"+ManageJob.TARGET_PartTimeJob)%>
</script>
</body>
</html>