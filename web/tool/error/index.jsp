<%@ page import="tool.Tool" %>
<%@page isErrorPage="true" %>
<%--
  Created by IntelliJ IDEA.
  User: wuhaolin
  Date: 7/7/14
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Tool.log(exception);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <script src="../../lib/js/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="../../lib/css/semantic.min.css">
  <script src="../../lib/js/semantic.min.js"></script>
  <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
</head>
<body>

<div class="ui page dimmer visible active">
  <div class="content">
    <div class="center">
      <h2 class="ui inverted icon header"><i class="icon circular inverted bug blue"></i>出现点小问题
        <div class="sub header">我们会尽快清理这只bug</div>
      </h2>
    </div>
  </div>
</div>

</body>
</html>
