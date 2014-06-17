<%--搜索图书列表--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  MyDate: 1/15/14
  Time: 10:21 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	String want = request.getParameter("W");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="../../lib/css/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
	<script src="../../lib/js/jquery.min.js"></script>
	<script src="../../lib/js/semantic.min.js"></script>
	<script src="../../lib/js/main.js"></script>
	<title>搜索图书</title>
</head>
<body>

<div class="ui three column stackable page grid">

	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetSearchBooksForAJAX.jsp">
		<jsp:param name="W" value="<%=want%>"/>
		<jsp:param name="page" value="1"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="ui button fluid circular" onclick="ajaxMore(this)" page="0" style="margin-top: 10px">更多
	</button>
	<div class="ui divider horizontal icon inverted"><a href="JianGou.jsp"><i class="icon book"></i></a></div>

</div>

<script>
	<%--ajax加载更多--%>
	function ajaxMore(btn) {
		$(btn).addClass('loading');
		var page = Number($(btn).attr('page'));
		page++;
		$.ajax({
			type: 'POST',
			url: "GetSearchBooksForAJAX.jsp",
			data: { W: '<%=want%>', page: page},
			contentType: "application/x-www-form-urlencoded; charset=utf-8"
		}).done(function (data) {
			$(btn).removeClass('loading');
			if (data.length < 20) {
				$(btn).text("没有更多了!");
				$(btn).addClass('disabled');
			} else {
				$(btn).before(data);
				$(btn).text("更多");
				$(btn).attr('page', page);
			}
		});
	}
</script>

</body>
</html>