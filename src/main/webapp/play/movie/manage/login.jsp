<%@ page import="play.movie.ManageMovie" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-2-5
  Time: 上午11:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String info = request.getParameter("info");
  if (info == null || info.length() < 1) {
    info = "登入进管理电影后台";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link href="../../../lib/css/bootstrap.min.css" rel="stylesheet">
  <script src="../../../lib/js/jquery.min.js"></script>
  <script src="../../../lib/js/bootstrap.min.js"></script>
  <link href="../../../lib/css/main.css" rel='stylesheet'>
  <script src="../../../lib/js/main.js"></script>
  <title>登入电影管理系统</title>
  <style>
    .input-group {
      margin: 10px;
    }

    .radio {
      margin: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="alert alert-danger"><%=info%>
  </div>
  <form class="form-group" action="LoginServlet.jsp" method="get">
    <div class="input-group">
      <span class="input-group-addon">密码</span>
      <input type="password" name="password" class="form-control">
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="target" value="<%=ManageMovie.Target_LTDYC%>" checked="checked">
        露天电影场
      </label>
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="target" value="<%=ManageMovie.Target_HDQNJC%>">
        华大青年剧场
      </label>
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="target" value="<%=ManageMovie.Target_iWANT%>">
        同学们想要的电影
      </label>
    </div>
    <button type="submit" class="form-control btn-primary">登 入</button>
  </form>
  <br>
  <a class="form-control btn-info" href="../list.jsp?target=iWANT">同学们想要的电影</a>
</div>
</body>
</html>
