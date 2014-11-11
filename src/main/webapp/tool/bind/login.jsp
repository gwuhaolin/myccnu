<%--绑定帐号密码登入界面--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 5:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link href="../../lib/css/bootstrap.min.css" rel="stylesheet">
  <link href="../../lib/css/main.css" rel='stylesheet'>
  <title>绑定帐号密码</title>
</head>
<body>
<div class="container">
  <br>
  <img src="img/profle.png" class="img-responsive center-block">
  <br>

  <form class="form-group" action="bind.jsp" method="post">
    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
      <input class="form-control input-lg" type="text" name="XH" placeholder="你的学号" maxlength="10">
    </div>
    <br>

    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
      <input class="form-control input-lg" type="password" name="MM" placeholder="你的信息门户密码">
    </div>
    <br>
    <input type="submit" class="form-control btn-info input-lg" id="submitBtn" onclick="loading(this)">
  </form>
  <%
    String info = request.getParameter("info");
    if (info != null) {
  %>
  <div class="alert alert-danger"><%=info%>
  </div>
  <%
    }
  %>


  <script src="../../lib/js/jquery.min.js"></script>
  <script src="../../lib/js/bootstrap.min.js"></script>
  <script src="../../lib/js/main.js"></script>

</div>
</body>
</html>
