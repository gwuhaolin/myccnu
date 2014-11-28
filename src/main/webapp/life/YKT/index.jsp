<%--登入一卡通后进入的页面--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 5:12 PM
--%>
<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="life.YKT.MyYktEntity" %>
<%@ page import="tool.Tool" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">

    <title>我的一卡通</title>
</head>
<body>
<%
    String XHMM[] = Tool.getXHMMfromCookie(request);
    if (Tool.XHMMisOK(XHMM)) {
        MyYktEntity result = ManageYKT.spiderAndGet(XHMM[0], XHMM[1]);
        if (result != null) {
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
            <a href="list.jsp?type=<%=ManageYKT.Type_Detail%>">
                <i class="icon yuan circular inverted red"></i>
            </a>

            <div class="ui label circular red">消费明细</div>
        </div>
    </div>

    <div class="column">
        <div class="ui header icon center aligned huge">
            <a href="list.jsp?type=<%=ManageYKT.Type_HelpMoney%>">
                <i class="icon list huge circular inverted green"></i>
            </a>

            <div class="ui label circular green">补助明细</div>
        </div>
    </div>

    <div class="column">
        <div class="ui header icon center aligned huge">
            <a href="list.jsp?type=<%=ManageYKT.Type_KaoQin%>">
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
} else {//数据库缓存也没有数据
%>
<script>
    alertMsg("暂时没有找到你的任何数据");
</script>
<%
        }
    } else {//没有绑定
        Tool.jspWriteJSForHTML_shouldBind(response, "");
    }
%>
<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>
</body>
</html>
