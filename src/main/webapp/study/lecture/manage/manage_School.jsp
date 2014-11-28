<%@ page import="study.lecture.ManageEvent" %>
<%@ page import="tool.R" %>
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
    <title>管理学院活动信息</title>

    <script>
        var changeCount = Number(<%=R.ChangeCount%>);
        <%--ajax加载更多--%>
        function ajaxMore(btn) {
            $(btn).addClass('active');
            $(btn).text("正在努力加载中...");
            var begin = Number($(btn).attr('begin'));
            begin += changeCount;
            $.ajax({
                url: "GetEventsManageFormForAJAX.jsp",
                data: {begin: begin, size: changeCount, target:<%=ManageEvent.TARGET_School%>},
                contentType: "application/x-www-form-urlencoded; charset=utf-8"
            }).done(function (data) {
                if (data.length < 20) {
                    $(btn).text("没有更多了!");
                } else {
                    $(btn).before(data);
                    $(btn).removeClass('active');
                    $(btn).text("更多");
                    $(btn).attr('begin', begin);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <%--添加--%>
    <div class="panel panel-info">
        <form action="ManageServlet.jsp" method="post" class="form-group">
            <%--id--%>
            <input type="hidden" name="id" value="-1">
            <%--cmd--%>
            <input type="hidden" name="cmd" value="<%=ManageEvent.CMD_Add%>">
            <%--target--%>
            <input type="hidden" name="target" value="<%=ManageEvent.TARGET_School%>">

            <%--名称--%>
            <div class="input-group">
                <span class="input-group-addon">名称</span>
                <input type="text" name="name" class="form-control">
            </div>


            <%--简介--%>
            <div class="input-group">
                <span class="input-group-addon">简介</span>
                <input type="text" name="intro" class="form-control">
            </div>


            <%--时间--%>
            <div class="input-group">
                <span class="input-group-addon">时间</span>
                <input type="text" name="runDate" class="form-control">
            </div>

            <%--地点--%>
            <div class="input-group">
                <span class="input-group-addon">地点</span>
                <input type="text" name="runLocation" class="form-control">
            </div>


            <%--来自--%>
            <div class="input-group">
                <span class="input-group-addon">发起者</span>
                <input type="text" name="manager" class="form-control">
            </div>

            <%--其他--%>
            <div class="input-group">
                <span class="input-group-addon">备注</span>
                <input type="text" name="otherInfo" class="form-control">
            </div>
            <input type="submit" class="form-control btn-primary" value="添加">

        </form>
    </div>

    <%--默认拿出前changeCount通知--%>
    <jsp:include page="GetEventsManageFormForAJAX.jsp">
        <jsp:param name="target" value="<%=ManageEvent.TARGET_School%>"/>
        <jsp:param name="begin" value="0"/>
        <jsp:param name="size" value="<%=R.ChangeCount%>"/>
    </jsp:include>
    <%=Tool.makeAjaxLoadMoreBtnHtml()%>
</div>
</body>
</html>
