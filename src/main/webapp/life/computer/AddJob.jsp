<%@ page import="life.repairComputer.ManageRepair" %>
<%@ page import="life.repairComputer.RepairComputerMansEntity" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/29
  Time: 21:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link href="../../lib/css/semantic.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">

	<style>
		body {
			background-color: #ffffff;
		}
	</style>
</head>
<body>
<%
	String org = request.getParameter("org");
	RepairComputerMansEntity one = ManageRepair.addJob(org);
%>
<div class="ui one column page grid center aligned">
	<div class="column">
		<div class="ui stacked segment">
			<%
				if (one.getOrg().equals(ManageRepair.Org_BenTen)) {
			%>
			<div class="ui header icon blue huge" onclick="showSearchBox()">
				<i class="icon rocket circular"></i>

				<div class="content">奔腾
					<div class="sub header">计算机学院奔腾电脑维修团队</div>
				</div>
			</div>
			<%
			} else if (one.getOrg().equals(ManageRepair.Org_XinGuang)) {
			%>
			<div class="ui header icon orange huge" style="color: #ffff00" onclick="showSearchBox()">
				<i class="icon star circular"></i>

				<div class="content">星光
					<div class="sub header">教育信息技术学院星光电脑服务队</div>
				</div>
			</div>
			<%
				}
			%>

			<div class="ui divided animated selection list">
				<div class="item">
					<i class="icon user red"></i><%=one.getName()%>
				</div>
				<div class="item">
					<i class="icon phone blue"></i><%=one.getPhone()%>
				</div>
				<div class="item">
					<i class="icon location green"></i><%=one.getAddress()%>
				</div>
			</div>

		</div>
		<div class="ui text">
			我已经把你的请求告诉了该队员,Ta可能会主动联系你
			但出于礼貌你应该主动联系Ta,并主动把电脑送到Ta手里
		</div>
	</div>
</div>

<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>

</body>
</html>
