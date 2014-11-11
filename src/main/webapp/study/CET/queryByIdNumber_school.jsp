<%--由于数据库里没有该同学的信息就直接用身份证号去学校的网站抓取--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 9:25 PM
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
  <title>查询结果</title>

</head>
<body>
<div class="container">
  <img src="img/rocket.png" class="img-responsive center-block">
  <br>

  <form class="form-group" action="result_IdNumber.jsp" method="get">
    <input type="text" maxlength="20" name="id" class="form-control input-lg"
           placeholder="你的身份证号码">
    <br>
    <label>
      <input type="radio" name="grade" value="4" checked>CET4
    </label>
    <label>
      <input type="radio" name="grade" value="6">CET6
    </label>
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
</div>
</body>
</html>
