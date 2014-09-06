<%--登入一卡通后进入的页面--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 5:12 PM
--%>
<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="tool.Tool" %>
<%@ page import="life.YKT.MyYktEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
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
  <title>我的一卡通</title>
</head>
<body>
<%
  String XHMM[] = Tool.getXHMMfromCookie(request);
  MyYktEntity result;
  if (Tool.XHMMisOK(XHMM)) {
    result = ManageYKT.spiderState(XHMM[0], XHMM[1]);
%>
<div class="ui stackable three column page grid center aligned">

  <div class="column center aligned">

    <div class="ui statistic">
      <div class="number"><%=result.getRemainMoney()%>
      </div>
    </div>
    <div class="ui label circular black">余额
    </div>

  </div>

  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="myDetail.jsp">
        <i class="icon yuan circular inverted red"></i>
      </a>

      <div class="ui label circular red">消费明细</div>
    </div>
  </div>

  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="myHelpMoney.jsp">
        <i class="icon list huge circular inverted green"></i>
      </a>

      <div class="ui label circular green">补助明细</div>
    </div>
  </div>

  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="myKaoQin.jsp">
        <i class="icon tags huge circular inverted black"></i>
      </a>

      <div class="ui label circular black">刷卡考勤</div>
    </div>
  </div>

  <div class="ui divider horizontal icon">
    <i class="icon money circular"></i>
  </div>

</div>
<%
  } else {//没有绑定
    Tool.jspWriteJSForHTML_shouldBind(response, "");
  }
%>
</body>
</html>
