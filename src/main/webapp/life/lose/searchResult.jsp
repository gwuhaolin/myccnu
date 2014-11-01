<%@ page import="tool.R" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String want = request.getParameter("want");
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="../../lib/js/jquery.min.js"></script>
	<script src="../../lib/js/semantic.min.js"></script>
	<link href="../../lib/css/main.css" rel='stylesheet'>
	<link href="../../lib/css/semantic.min.css" rel="stylesheet">
	<script src="../../lib/js/main.js"></script>
	<title>招领平台搜索结果</title>
</head>
<body>
<div class="container">
	<br><br>
	<div class="ui segment inverted raised">
		<p>以下是包含了<%=want%>关键字的失物招领信息,看看有没有你想要的?
		</p>
	</div>
	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetSearchForAJAX.jsp">
		<jsp:param name="begin" value="0"/>
		<jsp:param name="want" value="<%=want%>"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="ui fluid button" onclick="ajaxMore(this)">更多</button>
	<br><br><br>
</div>

<%--链接--%>
<%@ include file="link.jsp" %>
<script>
	var changeCount = Number(<%=R.ChangeCount%>);
	var begin=0;
	<%--ajax加载更多--%>
	function ajaxMore(btn) {
		$(btn).addClass('active');
		$(btn).text("正在努力加载中...");
		begin += changeCount;
		$.ajax({
			url: "GetSearchForAJAX.jsp",
			data: { begin: begin, want: '<%=want%>'},
			contentType: "application/x-www-form-urlencoded; charset=utf-8"
		}).done(function (data) {
			if (data.length < 20) {
				$(btn).text("没有更多了!");
			} else {
				$(btn).before(data);
				$(btn).removeClass('active');
				$(btn).text("更多");
				$(btn).attr('begin', begin);
			}
		});
	}
</script>
</body>
</html>