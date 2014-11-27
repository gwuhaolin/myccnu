<%@ page import="life.lose.ManageLose" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 7:46 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../lib/js/jquery.min.js"></script>
    <script src="../../lib/js/semantic.min.js"></script>
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link href="../../lib/css/main.css" rel='stylesheet'>
    <script src="../../lib/js/main.js"></script>
    <title>掉了东西</title>
</head>
<body>
<div class="ui form" align="center">
    <br><br><br><br>

    <form action="AddOneServlet.jsp" method="post">
        <div class="ui segment">
            <!--问题描述-->
            <textarea name="des" style="height: 150px" placeholder="描述一下你心爱的物品"></textarea>
            <!--失物地点-->
            <div class="ui input icon">
                <input type="text" name="location" placeholder="你在什么地方掉了你的物品">
                <i class="icon location"></i>
            </div>
            <!--联系方式-->
            <div class="ui input icon">
                <input type="text" name="phone" placeholder="你的电话">
                <i class="ui icon mobile"></i>
            </div>
        </div>
        <input type="hidden" name="type" value="<%=ManageLose.TYPE_Lose%>">
        <button type="submit" class="ui blue submit fluid button">提交</button>
        <br><br>
    </form>
</div>
<%--链接--%>
<%@ include file="link.jsp" %>

</body>
</html>
