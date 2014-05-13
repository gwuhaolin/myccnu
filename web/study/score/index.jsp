<%--查询期末成绩,结果界面--%>
<%@ page import="tool.Tool" %>
<%@ page import="study.score.GetScore" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 1/26/14
  Time: 11:00 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="/lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="/lib/js/jquery-2.0.2.min.js"></script>
	<script src="/lib/js/bootstrap.min.js"></script>
	<link href="/lib/css/main.css" rel='stylesheet'>
	<script src="/lib/js/main.js"></script>
	<title>成绩查询</title>
</head>
<body>
<%
	String XHMM[] = Tool.getXHMMfromCookie(request);
	if (Tool.XHMMisOK(XHMM)) {
		try {
			List<GetScore.MyScore> allScore = GetScore.get(XHMM[0], XHMM[1]);
%>
<div class="container">
	<%
		for (GetScore.MyScore score : allScore) {
	%>
	<div class="panel <%=score.getBootStrapPanelType()%>">
		<div class="panel-heading"><h4><span
				class="glyphicon <%=score.getBootStrapIconName()%>"></span> <%=score.getName()%>
		</h4></div>
		<div class="panel-body">

			<div class="btn-group">
				<button class="btn btn-info active"><span class="glyphicon glyphicon-th-large"></span> 总评</button>
				<button class="btn active"><%=score.getSumScore()%>
				</button>
			</div>

			<div class="btn-group">
				<button class="btn btn-info active"><span class="glyphicon glyphicon-tint"></span> 平时</button>
				<button class="btn active"><%=score.getPSScore()%>
				</button>
			</div>

			<div class="btn-group">
				<button class="btn btn-info active"><span class="glyphicon glyphicon-fire"></span> 期末</button>
				<button class="btn active"><%=score.getQMScore()%>
				</button>
			</div>

			<div class="btn-group">
				<button class="btn btn-info active"><span class="glyphicon glyphicon-heart-empty"></span> 学分</button>
				<button class="btn active"><%=score.getCredit()%>
				</button>
			</div>

		</div>
	</div>
	<%
		}
	%>
</div>
<%
		} catch (Exception e) {
			Tool.jspWriteJSForHTML_alertMsg(response, e.getMessage());
			return;
		}
	} else {
		Tool.jspWriteJSForHTML_shouldBind(response, "");
		return;
	}
%>

</body>
</html>