<%@ page import="life.repairComputer.ManageRepair" %>
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
	<title>电脑维修</title>
	<style>
		body {
			background-color: #ffffff;
		}
	</style>
</head>
<body>

<div class="ui one column page grid center aligned">

	<div class="column">
		<div class="ui header icon blue huge" onclick="showSearchBox('<%=ManageRepair.Org_BenTen%>')">
			<i class="icon rocket circular"
			   onmouseover="$(this).transition('set looping').transition('horizontal flip')"></i>

			<div class="content">奔腾
				<div class="sub header">计算机学院奔腾电脑维修团队</div>
			</div>
		</div>
	</div>


	<div class="ui divider icon horizontal"><i class="icon desktop"></i></div>

	<div class="column">
		<div class="ui header icon orange huge" style="color: #ffff00" onclick="showSearchBox('<%=ManageRepair.Org_XinGuang%>')">
			<i class="icon star circular" onmouseover="$(this).transition('set looping').transition('fade')"></i>

			<div class="content">星光
				<div class="sub header">教育信息技术学院星光电脑服务队</div>
			</div>
		</div>
	</div>

</div>

<div>
	<div class="ui styled sidebar floating">
		<div class="ui header center aligned icon">
			<i class="icon circular heart red inverted"
			   onmouseover="$(this).transition('set looping').transition('pulse')"></i>
		</div>
		<form class="ui form" method="post" action="AddJob.jsp">
			<div class="field">
				<textarea name="des" placeholder="输入你电脑问题的描述"></textarea>
			</div>
			<div class="field">
				<input name="connect" type="text" placeholder="输入你的联系方式">
			</div>
			<div class="field">
				<input type="submit" class="ui button fluid black">
			</div>
			<div class="ui error message"></div>
			<input type="hidden" name="org" id="org" value="">
		</form>
	</div>

	<i class="ui desktop icon link circular" onclick="showSearchBox()"
	   style="position: fixed;bottom: 5px;right: 5px" id="toggleIcon"></i>

	<script>
		function showSearchBox(org) {
			$('#org').val(org);
			$('.sidebar').first().sidebar({
				overlay: true
			}).sidebar('toggle');
			var toggleIcon = $('#toggleIcon');
			$(toggleIcon).toggleClass('red');
			$(toggleIcon).toggleClass('add');
			$(toggleIcon).toggleClass('remove ');
		}

		$('.ui.form')
				.form({
					des: {
						identifier: 'des',
						rules: [
							{
								type: 'empty',
								prompt: '你的电脑出现了什么问题呢?'
							}
						]
					},
					connect: {
						identifier: 'connect',
						rules: [
							{
								type: 'empty',
								prompt: '我要怎么联系你呢?'
							}
						]
					}
				});
	</script>
</div>

</body>
</html>