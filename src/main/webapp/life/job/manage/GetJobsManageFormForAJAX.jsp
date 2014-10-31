<%--通知列表里获得AJAX通知--%>
<%@ page import="java.util.List" %>
<%@ page import="life.jobs.MyJobEntity" %>
<%@ page import="life.jobs.ManageJob" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		int begin = Integer.parseInt(request.getParameter("begin"));
		int target = Integer.parseInt(request.getParameter("target"));
		List<MyJobEntity> jobEntities = ManageJob.get_OrderByID(begin, target);
		for (MyJobEntity one : jobEntities) {
%>
<div class="panel panel-info">
	<%--修改表单--%>
	<form action="ManageServlet.jsp" method="post" class="form-group">
		<%--id--%>
		<input type="hidden" name="id" value="<%=one.getId()%>">
		<%--cmd--%>
		<input type="hidden" name="cmd" value="<%=ManageJob.CMD_Change%>">
		<%--target--%>
		<input type="hidden" name="target" value="<%=target%>">

		<%--名称--%>
		<div class="input-group">
			<span class="input-group-addon">名称</span>
			<input type="text" name="name" class="form-control" value="<%=one.getName()%>">
		</div>
		<%--简介--%>
		<div class="input-group">
			<span class="input-group-addon">简介</span>
			<input type="text" name="jobInfo" class="form-control" value="<%=one.getJobInfo()%>">
		</div>


		<%--时间--%>
		<div class="input-group">
			<span class="input-group-addon">时间</span>
			<input type="text" name="jobTime" class="form-control" value="<%=one.getJobTime()%>">
		</div>

		<%--地点--%>
		<div class="input-group">
			<span class="input-group-addon">地点</span>
			<input type="text" name="jobLocation" class="form-control" value="<%=one.getJobLocation()%>">
		</div>


		<%--报酬--%>
		<div class="input-group">
			<span class="input-group-addon">报酬</span>
			<input type="text" name="money" class="form-control" value="<%=one.getMoney()%>">
		</div>

		<%--来自--%>
		<div class="input-group">
			<span class="input-group-addon">来自</span>
			<input type="text" name="manager" class="form-control" value="<%=one.getManager()%>">
		</div>

		<%--其他--%>
		<div class="input-group">
			<span class="input-group-addon">其他</span>
			<input type="text" name="otherInfo" class="form-control" value="<%=one.getOtherInfo()%>">
		</div>
		<input type="submit" class="form-control btn-warning" value="修改">

	</form>

	<%--删除表单--%>
	<form action="ManageServlet.jsp" method="post">
		<%--id--%>
		<input type="hidden" name="id" value="<%=one.getId()%>">
		<%--cmd--%>
		<input type="hidden" name="cmd" value="<%=ManageJob.CMD_Delete%>">
		<%--target--%>
		<input type="hidden" name="target" value="<%=target%>">

		<input type="submit" class="form-control btn-danger" value="删除">
	</form>
</div>
<%
		}
	} catch (Exception e) {
		return;
	}
%>