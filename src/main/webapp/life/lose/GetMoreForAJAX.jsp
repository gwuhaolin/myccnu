<%--通知列表里获得AJAX通知--%>
<%@ page import="java.util.List" %>
<%@ page import="life.lose.MyLoseEntity" %>
<%@ page import="life.lose.ManageLose" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		int begin = Integer.parseInt(request.getParameter("begin"));
		byte type = Byte.parseByte(request.getParameter("type"));
		List<MyLoseEntity> loseEntities = ManageLose.get_page(begin, type);
		for (MyLoseEntity one : loseEntities) {
%>
<div class="ui piled segment" id="<%=one.getId()%>">
	<%--描述--%>
	<h4><%=one.getMyDes()%>
	</h4>
	<hr>
	<%--发布时间--%>
	<div class="ui right tiny labeled icon button">
		<i class="icon time"></i>
		<%=one.getMyDate()%>
	</div>
	<%--失物地点--%>
	<%
		if(!one.getMyLocation().equals("")){
	%>
	<div class="ui right tiny labeled icon button">
		<i class="icon location"></i>
		<%=one.getMyLocation()%>
	</div>
	<%
		}
	%>
	<%--联系方式--%>
	<%
		if(!one.getMyPhone().equals("")){
	%>
	<div class="ui right tiny labeled icon button" onclick="toCall(this)">
		<i class="icon mobile"></i>
		<%=one.getMyPhone()%>
	</div>
	<%
		}
	%>
	<%--目前状态--%>
	<div class="ui right  tiny labeled icon button" onclick="completeOne(this);">
		<i class="icon refresh"></i><%=one.stateString()%>
	</div>
	<%--失物还是招领--%>
	<div class="ui right tiny labeled icon button">
		<i class="icon <%=one.loseOrUpdateGl()%>"></i>
		<%=one.loseOrUpdateChinese()%>
	</div>
	<%--是否是今天的--%>
	<%
		if (one.today()) {
	%>
	<button class="ui tiny green button">Today</button>
	<%
		}
	%>
</div>
<%
		}
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
%>