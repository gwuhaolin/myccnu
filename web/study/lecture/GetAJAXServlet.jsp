<%--通知列表里获得AJAX通知--%>
<%@ page import="java.util.List" %>
<%@ page import="study.lecture.ManageEvent" %>
<%@ page import="study.lecture.MyEventEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		int begin = Integer.parseInt(request.getParameter("begin"));
		int target= Integer.parseInt(request.getParameter("target"));
		String want=request.getParameter("want");
		List<MyEventEntity> jobEntities;
		if (want==null){
			jobEntities= ManageEvent.get_page(begin, target);
		}else {
			jobEntities= ManageEvent.search_page(begin,target, want);
		}
		for (MyEventEntity one : jobEntities) {
%>
<div class="column">
	<div class="ui stacked segment">

		<div class="ui label corner inverted red icon"><i class="icon empty heart"></i></div>

		<!--标题-->
		<div class="ui header">
			<div class="content"><%=one.getName()%>
			</div>
		</div>

		<div class="ui relaxed divided tiny list">


			<%--来自--%>
			<%
				if (one.getManager() != null && !one.getManager().equals("null")) {
			%>
			<div class="item">
				<i class="user icon circular inverted green"></i>

				<div class="content">
					<a class="header"><%=one.getManager()%></a>

					<div class="description">负责人
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%--地点--%>
			<%
				if (one.getRunLocation() != null && !one.getRunLocation().equals("null")) {
			%>
			<div class="item">
				<i class="map marker icon circular inverted blue"></i>

				<div class="content">
					<a class="header"><%=one.getRunLocation()%></a>
					<!--地址-->
					<div class="description">地址
					</div>
				</div>
			</div>
			<%
				}
			%>

			<!--时间-->
			<%
				if (one.getRunDate() != null && !one.getRunDate().equals("null")) {
			%>
			<div class="item">
				<i class="time icon circular inverted blank"></i>

				<div class="content">
					<a class="header"><%=one.getRunDate()%></a>

					<div class="description">时间
					</div>
				</div>
			</div>
			<%
				}
			%>


			<%--其他--%>
			<%
				if (one.getOtherInfo() != null && !one.getOtherInfo().equals("null")) {
			%>
			<div class="item">
				<i class="tags icon circular inverted purple"></i>

				<div class="content">
					<a class="header"><%=one.getOtherInfo()%></a>

					<div class="description">其他
					</div>
				</div>
			</div>
			<%
				}
			%>


			<%
				if (one.getIntro() != null && !one.getIntro().equals("null")) {
			%>
			<%--简介--%>
			<div class="item">
				<i class="star icon circular inverted red"></i>

				<div class="content">
					<a class="header">简介</a>

					<div class="description"><%=one.getIntro()%>
					</div>
				</div>
			</div>
			<%
				}
			%>

		</div>

		<!--评论-->
		<div class="ui button icon circular inverted teal mini" onclick="toggleComment(this)"><i
				class="icon comment"></i></div>
		<!--评论框-->
		<iframe src="comment.jsp?id=<%=one.getId()%>">
		</iframe>

	</div>
</div>
<%
		}
	} catch (Exception e) {
		return;
	}
%>