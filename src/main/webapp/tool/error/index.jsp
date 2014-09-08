<%@ page import="tool.Tool" %>
<%@page isErrorPage="true" %>
<%--
  Created by IntelliJ IDEA.
  User: wuhaolin
  Date: 7/7/14
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Tool.log(exception);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<script src="http://cdn.bootcss.com/jquery/2.1.1-rc2/jquery.min.js"></script>
	<link href="http://cdn.bootcss.com/semantic-ui/0.16.1/css/semantic.min.css" rel="stylesheet">
	<script src="http://cdn.bootcss.com/semantic-ui/0.16.1/javascript/semantic.min.js"></script>


	<link rel="stylesheet" type="text/css" href="../lib/css/main.css">
	<script src="../lib/js/main.js"></script>

</head>
<body>

<div class="ui page dimmer visible active">
	<div class="content">
		<div class="center">
			<h2 class="ui inverted icon header"><i class="icon circular inverted bug blue"></i>出现点小问题
				<span class="sub header" style="display: block">我们会尽快清理这只bug</span>
			</h2>

			<div class="ui divider icon horizontal inverted"><i class="ui icon smile"></i></div>
			<div class="ui column">
				<a class="ui button blue small" href="/tool/joinUs/index.html">来和我们一起修BUG</a>
			</div>
			<div style="margin: 5px"></div>
			<div class="ui column">
				<a class="ui button green small" href="/tool/feedback/index.html">告诉我们你的建议或好点子</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>
