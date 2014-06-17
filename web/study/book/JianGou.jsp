<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/27/14
  Time: 10:54 PM
--%>
<%--荐购图书--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String info=request.getParameter("info");
	if (info==null){
		info="向学校荐购";
	}
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
	<title>向学校荐购图书</title>
</head>
<body>

<div class="ui stackable one column page grid">

	<div class="column center aligned">
		<div class="ui header icon">
			<i class="icon heart inverted red circular"></i>

			<div class="content"><%=info%>
				<div class="sub header">没有找到你想要的书，向学校荐购吧！我会把你的荐购请求转交给学校</div>
			</div>
		</div>
		<form class="ui form" action="JianGouServlet.jsp" method="post">
			<div class="field">
				<div class="ui input fluid icon">
					<input type="text" name="name" placeholder="书叫什么名字?">
					<i class="icon book"></i>
				</div>
			</div>
			<div class="field">
				<div class="ui input fluid icon">
					<input type="text" name="press" placeholder="哪个出版社的?">
					<i class="icon globe"></i>
				</div>
			</div>
			<div class="field">
				<div class="inline fields">
					<div class="field">
						<div class="ui radio checkbox">
							<input type="radio" name="lan" value="C" checked>
							<label>中文</label>
						</div>
					</div>
					<div class="field">
						<div class="ui radio checkbox">
							<input type="radio" name="lan" value="U">
							<label>外文</label>
						</div>
					</div>
				</div>
			</div>
			<div class="field">
				<input type="submit" class="ui button fluid">
			</div>
			<div class="ui error message"></div>
		</form>
	</div>

</div>
<script>
	$('.ui.checkbox')
			.checkbox()
	;
	$('.ui.form')
			.form({
				firstName: {
					identifier  : 'name',
					rules: [
						{
							type   : 'empty',
							prompt : '书叫什么名字?'
						}
					]
				},
				press: {
					identifier  : 'press',
					rules: [
						{
							type   : 'empty',
							prompt : '哪个出版社的?'
						}
					]
				}
			});
</script>
</body>
</html>