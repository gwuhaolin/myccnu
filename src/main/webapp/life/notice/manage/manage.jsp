<%@ page import="life.notice.ManageNotice" %>
<%@ page import="tool.Tool" %>
<%--通知列表--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 3:01 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <title>最新通知</title>
  <style>
    hr {
      margin: 0 0 2px 0;
    }

    a:link {
      color: GrayText;
    }
  </style>

</head>
<body>
<div class="container">

  <%--添加通知--%>
  <div class="panel panel-info">
    <form action="ManageServlet.jsp" method="post" class="form-group">
      <input type="hidden" name="cmd" value="<%=ManageNotice.CMD_Add%>">
      <input type="hidden" name="id" value="-1">
      <%--标题--%>
      <div class="input-group">
        <span class="input-group-addon">标题</span>
        <input type="text" name="title" class="form-control">
      </div>
      <%--正文--%>
      <div class="input-group">
        <span class="input-group-addon">正文</span>
        <textarea name="content" class="form-control">
        </textarea>
      </div>
      <%--时间--%>
      <div class="input-group">
        <span class="input-group-addon">时间</span>
        <input type="text" name="date" class="form-control">
      </div>
      <%--来自--%>
      <div class="input-group">
        <span class="input-group-addon">来自</span>
        <input type="text" name="fromSite" class="form-control">
      </div>
      <%--源地址URL--%>
      <div class="input-group">
        <span class="input-group-addon">源地址</span>
        <input type="text" name="orgUrl" class="form-control">
      </div>

      <input type="submit" class="form-control btn-info" value="添加">
    </form>
  </div>

  <%--修改和删除--%>
  <%--默认拿出前changeCount通知--%>
  <jsp:include page="GetNoticeManageFormForAJAX.jsp">
    <jsp:param name="begin" value="0"/>
  </jsp:include>

  <%--ajax 加载更多--%>
  <button class="form-control btn-info input-lg" onclick="ajaxMore(this)" begin="0">更多</button>
  <br>
</div>

<script>
  <%=Tool.makeAJAXLoadMoreJS("GetNoticeManageFormForAJAX.jsp","")%>
</script>

</body>
</html>
