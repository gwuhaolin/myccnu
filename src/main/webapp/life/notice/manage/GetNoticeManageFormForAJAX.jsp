<%--通知列表里获得AJAX通知--%>
<%@ page import="java.util.List" %>
<%@ page import="life.notice.MyNoticeEntity" %>
<%@ page import="life.notice.ManageNotice" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    try {
        int begin = Integer.parseInt(request.getParameter("begin"));
        List<MyNoticeEntity> notices = ManageNotice.get_page(begin);
        for (MyNoticeEntity one : notices) {
%>
<div class="thumbnail alert-warning">
    <div class="panel panel-info">
        <%--修改表单--%>
        <form action="ManageServlet.jsp" method="post" class="form-group">
            <input type="hidden" name="id" value="<%=one.getId()%>">
            <input type="hidden" name="cmd" value="<%=ManageNotice.CMD_Change%>">

            <%--标题--%>
            <div class="input-group">
                <span class="input-group-addon">标题</span>
                <input type="text" name="title" value="<%=one.getTitle()%>" class="form-control">
            </div>
            <%--正文--%>
            <div class="input-group">
                <span class="input-group-addon">正文</span>
        <textarea name="content" class="form-control"><%=one.getContent()%>
        </textarea>
            </div>
            <%--时间--%>
            <div class="input-group">
                <span class="input-group-addon">时间</span>
                <input type="text" name="date" value="<%=one.getDate()%>" class="form-control">
            </div>
            <%--来自--%>
            <div class="input-group">
                <span class="input-group-addon">来自</span>
                <input type="text" name="fromSite" value="<%=one.getFromSite()%>" class="form-control">
            </div>
            <%--源地址URL--%>
            <div class="input-group">
                <span class="input-group-addon">源地址</span>
                <input type="text" name="orgUrl" value="<%=one.getOrgUrl()%>" class="form-control">
            </div>

            <%--是否是今天的--%>
            <%
                if (one.today()) {
            %>
            <button class="btn btn-success btn-xs active">Today</button>
            <%
                }
            %>

            <input type="submit" class="form-control btn-warning" value="修改">
        </form>

        <%--删除表单--%>
        <form action="ManageServlet.jsp" method="post" class="form-group">
            <input type="hidden" name="id" value="<%=one.getId()%>">
            <input type="hidden" name="cmd" value="<%=ManageNotice.CMD_Delete%>">
            <input type="submit" class="form-control btn-danger" value="删除">
        </form>
    </div>
</div>
<%
        }
    } catch (Exception e) {
        return;
    }
%>
