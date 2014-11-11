<%--用考号去全国查询网站抓取--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/19/14
  Time: 9:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String info = request.getParameter("info");
%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link href="../../lib/css/bootstrap.min.css" rel="stylesheet">
  <script src="../../lib/js/jquery.min.js"></script>
  <script src="../../lib/js/bootstrap.min.js"></script>
  <link href="../../lib/css/main.css" rel='stylesheet'>
  <script src="../../lib/js/main.js"></script>
  <title>查CET成绩</title>
</head>
<body>
<div class="container">
  <img src="img/rocket.png" class="img-responsive center-block">
  <br>

  <form class="form-group" action="result_KH.jsp" method="post">
    <input type="text" maxlength="20" name="kh" class="form-control input-lg" placeholder="输入你的考号">
    <br>
    <input type="text" maxlength="8" name="name" class="form-control input-lg" placeholder="输入你的姓名">
    <br>
    <input type="submit" value="GO" class="form-control btn-info input-lg">
  </form>
  <%
    if (info != null) {
  %>
  <div class="alert alert-info"><%=info%>
  </div>
  <%
    }
  %>
  <a class="text-center center-block" href="http://cet.99sushe.com/faq/#find"><em>忘记考号?</em></a>
</div>
</body>
</html>
