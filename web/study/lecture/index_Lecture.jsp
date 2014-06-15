<%@ page import="study.lecture.ManageEvent" %>
<%@ page import="tool.R" %>
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
	<link rel="stylesheet" type="text/css" href="../../lib/css/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
	<script src="../../lib/js/jquery-1.11.2.min.js"></script>
	<script src="../../lib/js/semantic.min.js"></script>
	<script src="../../lib/js/main.js"></script>
	<title>最新讲座</title>
</head>
<body>
<div class="ui stackable three column page grid">

	<%--默认拿出前changeCount通知--%>
	<jsp:include page="GetAJAXServlet.jsp">
		<jsp:param name="target" value="<%=ManageEvent.TARGET_Lecture%>"/>
		<jsp:param name="begin" value="0"/>
	</jsp:include>
	<%--ajax 加载更多--%>
	<button class="ui button fluid circular" onclick="ajaxMore(this)" begin="0" style="margin-top: 10px">更多
	</button>
	<div class="ui divider horizontal icon inverted"><a href="index_School.jsp"><i class="icon smile"></i></a></div>

</div>
<script>
	<%--ajax加载更多--%>
	<%=Tool.makeAJAXLoadMoreJS("GetAJAXServlet.jsp",", target:"+ManageEvent.TARGET_Lecture)%>

	closeWeiXinBtn();

</script>

<jsp:include page="searchBox.jsp">
	<jsp:param name="target" value="<%=ManageEvent.TARGET_Lecture%>"/>
</jsp:include>

</body>
</html>
