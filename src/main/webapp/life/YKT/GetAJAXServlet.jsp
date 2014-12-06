<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="life.YKT.MyYktEntity" %>
<%@ page import="tool.R" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: wuhaolin
  Date: 11/28/14
  Time: 17:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String XHMM[] = Tool.getXHMMfromCookie(request);

    if (!Tool.XHMMisOK(XHMM)) {//没有绑定
        Tool.jspWriteJSForHTML_shouldBind(response, "");
        return;
    }
    int begin = Integer.parseInt(request.getParameter("begin"));
    int type = Integer.parseInt(request.getParameter("type"));
    List<MyYktEntity> re = ManageYKT.get(type, XHMM[0], begin, R.ChangeCount);

    if (re != null) {
        for (MyYktEntity one : re) {
%>
<div class="column">
    <div class="ui stacked segment">
        <%--是否是今天的--%>
        <%
            if (one.today()) {
        %>
        <div class="ui label corner icon inverted red">
            <i class="icon">N</i>
        </div>
        <%
            }
        %>

        <%--变化金额--%>
        <%
            if (one.getChangeMoney() != null) {
        %>
        <div class="ui statistic">
            <div class="number"><%=one.getChangeMoney()%>
            </div>
        </div>
        <%
            }
        %>
        <div class="ui relaxed divided list">
            <%
                if (one.getLocation() != null) {
            %>
            <%--发生地点--%>
            <div class="item">
                <i class="map marker icon circular inverted blue"></i>

                <div class="content">
                    <a class="header">地址</a>

                    <div class="description"><%=one.getLocation()%>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <%
                if (one.getTime() != null) {
            %>
            <%--发生时间--%>
            <div class="item">
                <i class="map time icon circular inverted green"></i>

                <div class="content">
                    <a class="header">时间</a>

                    <div class="description"><%=one.getTime()%>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <%--余额--%>
            <%
                if (one.getRemainMoney() != null) {
            %>
            <div class="item">
                <i class="map money icon circular inverted black"></i>

                <div class="content">
                    <a class="header">余额</a>

                    <div class="description"><%=one.getRemainMoney()%>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<%
    }//end for
} else if (begin == 0) {//数据库缓存也没有数据,而且是初次打开
%>
<script>
    alertMsg('暂时获取不到你的任何信息');
</script>
<%
    }
%>
