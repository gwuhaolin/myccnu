<%--一卡通消费明细--%>
<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="life.YKT.OneChange" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/22/14
  Time: 2:13 PM
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
	<title>一卡通消费明细</title>
</head>
<body>
<div class="ui stackable three column page grid">
	<%
		String XHMM[] = Tool.getXHMMfromCookie(request);
		if (Tool.XHMMisOK(XHMM)) {
			try {
				List<OneChange> changes = ManageYKT.getDetail(XHMM[0], XHMM[1]);
				for (int i = 0; i < changes.size(); i++) {
					OneChange one = changes.get(i);
	%>
	<div class="column">
		<div class="ui stacked segment">
			<%--是否是今天的--%>
			<%
				if (one.isTaday()) {
			%>
			<div class="ui label corner icon inverted red">
				<i class="icon">N</i>
			</div>
			<%
				}
			%>

			<div class="ui statistic">
				<div class="number"><%=one.getChangeMoney()%>
				</div>
			</div>
			<div class="ui relaxed divided list">
				<div class="item">
					<i class="map marker icon circular inverted blue"></i>

					<div class="content">
						<a class="header">地址</a>

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
		alertMsg('<%=e.getMessage()%>');
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