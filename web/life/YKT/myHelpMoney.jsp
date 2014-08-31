<%--补助明细--%>
<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="life.YKT.MyYktEntity" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/22/14
  Time: 2:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>

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
	<title>补助明细</title>
</head>
<body>
<div class="ui stackable three column page grid">
	<%
		String XHMM[] = Tool.getXHMMfromCookie(request);
		if (Tool.XHMMisOK(XHMM)) {
			try {
				List<MyYktEntity> changes = ManageYKT.spiderHelpMoney(XHMM[0], XHMM[1]);
				for (int i = 0; i < changes.size(); i++) {
					MyYktEntity one = changes.get(i);
	%>
	<div class="column">
		<div class="ui stacked segment">
			<div class="ui statistic">
				<div class="number"><%=one.getChangeMoney()%>
				</div>
			</div>
			<div class="ui relaxed divided list">
				<div class="item">
					<i class="icon tag circular inverted blue"></i>

					<div class="content">
						<a class="header">名称</a>

						<div class="description"><%=one.getLocation()%>
						</div>
					</div>
				</div>
				<div class="item">
					<i class="map time icon circular inverted green"></i>

					<div class="content">
						<a class="header">时间</a>

						<div class="description"><%=one.getTime()%>
						</div>
					</div>
				</div>
				<div class="item">
					<i class="map money icon circular inverted black"></i>

					<div class="content">
						<a class="header">余额</a>

						<div class="description"><%=one.getRemainMoney()%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	} catch (Exception e) {
	%>
	<script>
		alertMsg('~囧~太多人问我我都反应不过来了');
	</script>
	<%
			}
		} else {//没有绑定
			Tool.jspWriteJSForHTML_shouldBind(response, "");
		}
	%>
</div>
</body>
</html>
