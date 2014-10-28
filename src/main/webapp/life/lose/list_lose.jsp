<%@ page import="tool.R" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<title>大家掉了</title>
	<script>
		var changeCount = Number(<%=R.ChangeCount%>);
		<%--ajax加载更多--%>
		function ajaxMore(btn) {
			$(btn).addClass('active');
			$(btn).text("正在努力加载中...");
			var begin = Number($(btn).attr('begin'));
			begin += changeCount;
			$.ajax({
				url: "GetMoreForAJAX.jsp",
				data: { begin: begin, type: <%=ManageLose.TYPE_Lose%>},
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
</head>
<body>
<div class="container">
	<br><br>
	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetMoreForAJAX.jsp">
		<jsp:param name="begin" value="0"/>
		<jsp:param name="type" value="<%=ManageLose.TYPE_Lose%>"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="ui fluid button" onclick="ajaxMore(this)" begin="0">更多</button>
	<br><br><br>
</div>

<%--链接--%>
<%@ include file="link.jsp" %>


</body>
</html>