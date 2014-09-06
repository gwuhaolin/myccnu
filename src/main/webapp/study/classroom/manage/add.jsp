<%@ page import="study.classroom.ManageClassroom" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/31
  Time: 17:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="../../../lib/css/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="../../../lib/css/main.css">
	<script src="../../../lib/js/jquery.min.js"></script>
	<script src="../../../lib/js/semantic.min.js"></script>
	<script src="../../../lib/js/main.js"></script>
	<title>添加空余教室</title>
</head>
<body>

<div class="column">
	<%
		String info=request.getParameter("info");
	%>
	<div class="ui message warning"><%=info%></div>

	<form class="ui form" action="AddOneServlet.jsp" method="post">
		<div class="fields">
			<div class="field">
				<select name="JiHaoLou" class="ui button fluid small">
					<option selected>哪栋楼?</option>
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
				<select name="XinQiJi" class="ui button fluid small">
					<option selected>星期几?</option>
					<option value="1">星期1</option>
					<option value="2">星期2</option>
					<option value="3">星期3</option>
					<option value="4">星期4</option>
					<option value="5">星期5</option>
				</select>
			</div>

			<div class="field">
				<select name="DiJiJie" class="ui button fluid small">
					<option selected>第几节课的时候?</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
				</select>
			</div>

			<div class="field">
				<input type="text" name="Classroom" placeholder="教室的名字是?">
			</div>

			<div class="field">
				<input type="submit" class="ui black fluid button" value="添加"/>
			</div>
			<div class="ui error message"></div>

		</div>
	</form>

	<a href="all.jsp" class="ui button">所有</a>

</div>
<script>
	$('.ui.form')
			.form({
				Classroom: {
					identifier: 'Classroom',
					rules: [
						{
							type: 'empty',
							prompt: '教室的名字是?'
						}
					]
				}
			});
</script>
</body>
</html>