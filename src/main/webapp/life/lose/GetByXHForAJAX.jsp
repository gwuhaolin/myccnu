<%--通知列表里获得AJAX通知--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="life.lose.ManageLose" %>
<%@ page import="life.lose.MyLoseEntity" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		List<MyLoseEntity> loseEntities = ManageLose.get(Tool.getXHMMfromCookie(request)[0]);
		if (loseEntities.size() > 0) {
%>
<div class="ui header">以下是你所有的失物招领信息,如果你已经成功完成失物招领请点黄色状态按钮确认完成</div>
<%
	for (MyLoseEntity one : loseEntities) {
%>
<div class="ui segment raised" id="<%=one.getId()%>" onclick="addComment(this)">
	<%--描述--%>
	<h4><%=one.getMyDes()%>
	</h4>
	<hr>

	<div class="ui labels">
		<%--联系方式--%>
		<%
			if (!one.getMyPhone().equals("")) {
		%>
		<a class="ui small label blue" href="tel:<%=one.getMyPhone()%>">
			<i class="icon mobile"></i>
			<%=one.getMyPhone()%>
		</a>
		<%
			}
		%>
		<%--发布时间--%>
		<div class="ui small label ">
			<i class="icon time"></i>
			<%=one.getMyDate()%>
		</div>
		<%--失物地点--%>
		<%
			if (!one.getMyLocation().equals("")) {
		%>
		<div class="ui small label ">
			<i class="icon location"></i>
			<%=one.getMyLocation()%>
		</div>
		<%
			}
		%>
		<%--是否是今天的--%>
		<%
			if (one.today()) {
		%>
		<div class="ui label green">Today</div>
		<%
			}
		%>
	</div>

</div>
<%
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
%>