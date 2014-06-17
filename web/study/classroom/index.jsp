<%@ page import="java.util.List" %>
<%@ page import="study.classroom.OneQueryResult" %>
<%@ page import="study.classroom.ManageClassroom" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/29
  Time: 16:46
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
	<script src="../../lib/js/jquery.min.js"></script>
	<script src="../../lib/js/semantic.min.js"></script>
	<script src="../../lib/js/main.js"></script>
	<title>空教室查询</title>
</head>
<body>
<%
	String location = request.getParameter("location");
	if (location == null) {
		location = ManageClassroom.JIHAOLOU[0];
	}
	int time;
	try {
		time = Integer.parseInt(request.getParameter("time"));
	} catch (Exception e) {
		time = Tool.week_NOW() - 1;
	}
	List<OneQueryResult> all = ManageClassroom.query(time, location);
%>
<div class="ui three column center aligned stackable page grid">

	<div class="column">
		<div class="ui message"><%=location%>号楼,星期<%=time%> 的空教室</div>
	</div>

	<%
		for (OneQueryResult one : all) {
	%>
	<div class="column">
		<div class="ui stacked segment animated large ">
			<div class="ui statistic">
				<div class="number"><%=one.getDiJiJieke()%>
				</div>
				<div class="description"><%=OneQueryResult.dijijiekeToString(one.getDiJiJieke())%>
				</div>
			</div>
			<div class="ui divided selection list animated">
				<%
					List<String> allClassroom = one.getAllClassRoom();
					for (String oneClassroom : allClassroom) {
				%>
				<a class="item"><%=oneClassroom%>
				</a>
				<%
					}
				%>
			</div>

		</div>
	</div>
	<%
		}
	%>

</div>

<%--隐藏于左边的功能框--%>
<div>
	<%--隐藏于左边的搜索框--%>
	<div class="ui styled sidebar floating">
		<div class="ui header center aligned icon">
			<i class="icon circular calendar blue inverted"></i>
		</div>
		<form class="ui form" method="post" action="index.jsp">

			<div class="field">
				<select name="location" class="ui button fluid small">
					<option value="" selected>哪栋楼?</option>
					<%
						for (String one : ManageClassroom.JIHAOLOU) {
					%>
					<option value="<%=one%>"><%=one%>号楼</option>
					<%
						}
					%>
				</select>
			</div>

			<div class="field">
				<select name="time" class="ui button fluid small">
					<option value="" selected>星期几?</option>
					<option value="1">星期1</option>
					<option value="2">星期2</option>
					<option value="3">星期3</option>
					<option value="4">星期4</option>
					<option value="5">星期5</option>
				</select>
			</div>

			<div class="field">
				<input type="submit" class="ui black fluid button" value="GO"/>
			</div>

		</form>
	</div>

	<%--打开隐藏于左边的搜索框的按钮--%>
	<i class="ui add sign icon link big" onclick="showSearchBox(this)"
	   style="position: fixed;bottom: 5px;right: 5px"></i>

	<script>
		closeWeiXinBtn();
		function showSearchBox(btn) {
			$('.sidebar').first().sidebar({
				overlay: true
			}).sidebar('toggle');
			$(btn).toggleClass('red');
			$(btn).toggleClass('add');
			$(btn).toggleClass('remove ');
		}
	</script>
</div>

</body>
</html>

