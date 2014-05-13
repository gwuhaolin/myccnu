<%@ page import="tool.Tool" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String XH= Tool.getXHMMfromCookie(request)[0];
%>
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
	<title>加入我们</title>
	<style>
		body{
			background: url("img/back.gif");
			background-color: #000000;
			color: #f5f5f5;
		}
		p{
			background-color: transparent;
			font-size: 20px;
			font-style: italic;
			font-weight: bolder;
			font-family: Comic Sans MS
		}
	</style>
	<script>
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			WeixinJSBridge.call('hideToolbar');
		});
	</script>
</head>
<body>
<div class="container">
	<div class="jumbotron" style="background-color: transparent">
		<h1>加入我们</h1>
		<p>大学怎能平平淡淡?加入我们一起疯狂吧!</p>
	</div>

	<!--技术宅-->
	<div class="row">
		<p class="col-xs-4 col-xs-offset-2" style="color: lightblue">如果你因为写代码而忘记妹子 <button class="btn btn-info btn-sm">开发</button></p>
		<img src="img/dev.png" class="img-responsive col-xs-5">
	</div>
	<br>
	<!--美工狗-->
	<div class="row">
		<img src="img/art.png" class="img-responsive col-xs-7">
		<p class="col-xs-4" style="color: sandybrown">如果你有强迫症为了一幅图而耗上一天以追求完美 <button class="btn btn-warning btn-sm">美工</button></p>
	</div>
	<br>
	<!--热情-->
	<div class="row">
		<p class="col-xs-4 col-xs-offset-1" style="color: indianred">如果你满腔热血充满激情每天像打了鸡血 <button class="btn btn-danger btn-sm">营运</button></p>
		<img src="img/flame.png" class="img-responsive col-xs-6">
	</div>
	<br>
	<!--表单-->
	<form action="JoinUsServlet.jsp" method="post" class="form-inline">
		<input type="text" class="form-control input-lg" name="XH" placeholder="输入你的学号" value="<%=XH%>">
		<input type="submit" class="form-control btn-info input-lg" value="加入我们吧!">
	</form>
	<br>
</div>
</body>
</html>