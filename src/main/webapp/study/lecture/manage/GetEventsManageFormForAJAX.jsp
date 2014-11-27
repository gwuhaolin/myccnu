<%--通知列表里获得AJAX通知--%>
<%@ page import="study.lecture.ManageEvent" %>
<%@ page import="study.lecture.MyEventEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    try {
        int begin = Integer.parseInt(request.getParameter("begin"));
        int target = Integer.parseInt(request.getParameter("target"));
        List<MyEventEntity> jobEntities = ManageEvent.get_page(begin, target);
        for (MyEventEntity one : jobEntities) {
%>
<div class="panel panel-info">
    <%--修改表单--%>
    <form action="ManageServlet.jsp" method="post" class="form-group">
        <%--id--%>
        <input type="hidden" name="id" value="<%=one.getId()%>">
        <%--cmd--%>
        <input type="hidden" name="cmd" value="<%=ManageEvent.CMD_Change%>">
        <%--target--%>
        <input type="hidden" name="target" value="<%=target%>">

        <%--名称--%>
        <div class="input-group">
            <span class="input-group-addon">名称</span>
            <input type="text" name="name" class="form-control" value="<%=one.getName()%>">
        </div>

        <%--简介--%>
        <div class="input-group">
            <span class="input-group-addon">简介</span>
            <input type="text" name="intro" class="form-control" value="<%=one.getIntro()%>">
        </div>


        <%--时间--%>
        <div class="input-group">
            <span class="input-group-addon">时间</span>
            <input type="text" name="runDate" class="form-control" value="<%=one.getRunDate()%>">
        </div>

        <%--地点--%>
        <div class="input-group">
            <span class="input-group-addon">地点</span>
            <input type="text" name="runLocation" class="form-control" value="<%=one.getRunLocation()%>">
        </div>

        <%--来自--%>
        <div class="input-group">
            <span class="input-group-addon">发起者</span>
            <input type="text" name="manager" class="form-control" value="<%=one.getManager()%>">
        </div>

        <%--其他--%>
        <div class="input-group">
            <span class="input-group-addon">备注</span>
            <input type="text" name="otherInfo" class="form-control" value="<%=one.getOtherInfo()%>">
        </div>
        <input type="submit" class="form-control btn-warning" value="修改">

    </form>

    <%--删除表单--%>
    <form action="ManageServlet.jsp" method="post">
        <%--id--%>
        <input type="hidden" name="id" value="<%=one.getId()%>">
        <%--cmd--%>
        <input type="hidden" name="cmd" value="<%=ManageEvent.CMD_Delete%>">
        <%--target--%>
        <input type="hidden" name="target" value="<%=target%>">

        <input type="submit" class="form-control btn-danger" value="删除">
    </form>
</div>
<%
        }
    } catch (Exception e) {
        return;
    }
%>
