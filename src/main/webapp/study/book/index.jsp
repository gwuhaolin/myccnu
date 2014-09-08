<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/27/14
  Time: 10:48 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String info = request.getParameter("info");
	if (info == null) {
		info = "";
	}
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


	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
	<script src="../../lib/js/main.js"></script>
	<title>校园图书馆</title>
</head>
<body>

<div class="ui stackable one column page grid center aligned">


	<div class="column">
		<div class="ui header icon huge">
			<i class="icon search inverted circular green"></i>

			<div class="content"><%=info%>
			</div>
		</div>
		<form class="ui form" action="SearchBook.jsp" method="post">
			<div class="field">
				<div class="ui input fluid icon">
					<input type="text" class="input" name="W" placeholder="输入关键字搜索我校图书馆藏书">
					<i class="icon search"></i>
				</div>
			</div>
			<div class="field">
				<input type="submit" class="ui button fluid circular" onclick="loading(this)" value="搜索">
			</div>
			<div class="ui error message"></div>
		</form>
	</div>

	<div class="column">
		<div class="ui header icon huge">
			<a href="MyLibBook.jsp">
				<i class="icon book circular inverted"></i>
			</a>

			<div class="ui label circular black">我的图书馆</div>
		</div>
	</div>

</div>
<script>
	$('.ui.form')
			.form({
				W: {
					identifier: 'W',
					rules: [
						{
							type: 'empty',
							prompt: '你想要什么书呢?输入关键字吧~'
						}
					]
				}
			});
</script>
</body>
</html>