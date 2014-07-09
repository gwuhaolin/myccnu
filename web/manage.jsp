<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="lib/css/semantic.min.css">
	<link rel="stylesheet" type="text/css" href="lib/css/main.css">
	<script src="lib/js/jquery.min.js"></script>
	<script src="lib/js/semantic.min.js"></script>
	<title>掌上华师管理系统</title>
</head>
<body>

<div class="ui page stackable grid ">

	<div class="sixteen wide column">
		<div class="ui huge center aligned header icon">
			<i class="circular setting icon inverted"></i>

			<div class="content">掌上华师
				<div class="sub header">后台信息发布管理系统</div>
			</div>
		</div>
	</div>

	<div class="three column row center aligned">

		<div class="column">
			<div class="ui stacked segment red inverted">
				<div class="ui icon header">
					<i class="icon sun circular inverted"></i>
					爱生活
				</div>
				<div class="ui divided selection list">
					<a class="item" href="life/notice/manage/login.jsp">最新通知</a>
					<a class="item" href="life/job/manage/login.jsp">兼职家教</a>
				</div>
			</div>
		</div>

		<div class="column">
			<div class="ui stacked segment inverted green">
				<div class="ui icon header">
					<i class="icon book circular inverted"></i>
					做学霸
				</div>
				<div class="ui divided selection list">
					<a class="item" href="study/lecture/manage/login_School.jsp">校园活动</a>
					<a class="item" href="study/lecture/manage/login.jsp">讲座</a>
				</div>
			</div>
		</div>

		<div class="column">
			<div class="ui stacked segment purple inverted">
				<div class="ui icon header">
					<i class="icon gamepad circular inverted"></i>
					找乐子
				</div>
				<div class="ui divided selection list">
					<a class="item" href="play/movie/manage/login.jsp">校园电影</a>
					<a class="item" href="play/shudong/manage/login.jsp">树洞</a>
					<a class="item" href="http://wsq.qq.com/">掌上社区</a>
					<a class="item" href="play/vote/manage.html">投票</a>
				</div>
			</div>
		</div>

	</div>

	<div class="ui horizontal divider icon">
		<i class="icon red heart circular"></i>
	</div>

</div>


<script src="lib/js/main.js"></script>
</body>
</html>