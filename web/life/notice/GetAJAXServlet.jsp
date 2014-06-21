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
        String want = request.getParameter("want");
        List<MyNoticeEntity> notices;
        if (want == null) {//
            notices = ManageNotice.get_page(begin);
        } else {//如果有want说明是要搜索
            notices = ManageNotice.search_page(begin, want);
        }
        for (MyNoticeEntity one : notices) {
%>

<div class="column">
    <div class="ui stacked segment" onclick="openOneAJAX(<%=one.getId()%>)">
        <%--是否是今天的--%>
        <%
            if (one.taday()) {
        %>
        <div class="ui label corner inverted red left icon"><i class="icon">N</i></div>
        <%
            }
        %>

        <!--标题-->
        <div class="ui header">
            <div class="content"><%=one.toString()%>
            </div>
        </div>

        <div class="ui relaxed horizontal divided mini list">

            <%--来自--%>
            <div class="item">
                <i class="globe icon blue"></i>

                <div class="content">
                    <a class="header"><%=one.getFromSite()%>
                    </a>
                </div>
            </div>

            <!--时间-->
            <div class="item">
                <i class="time icon blank"></i>

                <div class="content">
                    <a class="header"><%=one.getDate()%>
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>

<%
        }
    } catch (Exception e) {
        return;
    }
%>