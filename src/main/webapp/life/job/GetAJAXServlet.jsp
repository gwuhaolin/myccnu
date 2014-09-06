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
		String want=request.getParameter("want");
		List<MyJobEntity> jobEntities;
		if(want==null){
			int target = Integer.parseInt(request.getParameter("target"));
			jobEntities = ManageJob.get_page(begin, target);
		}else {
			jobEntities=ManageJob.search_page(begin,want);
		}
		for (MyJobEntity one : jobEntities) {
%>
<div class="column">
	<div class="ui stacked segment">

		<%
			if (one.getJobInfo() != null && !one.getJobInfo().equals("null")) {
		%>
		<!--标题-->
		<div class="ui header">
			<div class="content"><%=one.getJobInfo()%>
			</div>
		</div>
		<%
			}
		%>


		<div class="ui relaxed divided mini list">

			<%
				if (one.getName() != null && !one.getName().equals("null")) {
			%>
			<div class="item">
				<i class="star icon circular inverted black"></i>

				<div class="content">
					<a class="header"><%=one.getName()%>
					</a>

					<div class="description">情况
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%
				if (one.getJobTime() != null && !one.getJobTime().equals("null")) {
			%>
			<!--时间-->
			<div class="item">
				<i class="time icon circular inverted teal"></i>

				<div class="content">
					<a class="header"><%=one.getJobTime()%>
					</a>

					<div class="description">时间
					</div>
				</div>
			</div>
			<%
				}
			%>


			<%
				if (one.getJobLocation() != null && !one.getJobLocation().equals("null")) {
			%>
			<div class="item">
				<i class="location icon circular inverted red"></i>

				<div class="content">
					<a class="header"><%=one.getJobLocation()%>
					</a>

					<div class="description">地点
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%
				if (one.getMoney() != null && !one.getMoney().equals("null")) {
			%>
			<div class="item">
				<i class="money icon circular inverted blue"></i>

				<div class="content">
					<a class="header"><%=one.getMoney()%>
					</a>

					<div class="description">报酬
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%
				if (one.getManager() != null && !one.getManager().equals("null")) {
			%>
			<div class="item">
				<i class="globe icon circular inverted green"></i>

				<div class="content">
					<a class="header"><%=one.getManager()%>
					</a>

					<div class="description">来自
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%
				if (one.getOtherInfo() != null && !one.getOtherInfo().equals("null")) {
			%>
			<div class="item">
				<i class="tags icon circular inverted purple"></i>

				<div class="content">
					<a class="header"><%=one.getOtherInfo()%>
					</a>

					<div class="description">其他
					</div>
				</div>
			</div>
			<%
				}
			%>

		</div>

	</div>
</div>

<%
		}
	} catch (Exception e) {
		return;
	}
%>