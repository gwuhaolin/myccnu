<%--用id获得单条通知详细--%>
<%@ page import="life.notice.MyNoticeEntity" %>
<%@ page import="life.notice.ManageNotice" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  MyDate: 1/14/14
  Time: 11:00 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  MyNoticeEntity noticeEntity = ManageNotice.get(id);
  if (noticeEntity == null) {
    return;
  }
%>
<div class="ui modal">
  <i class="close icon red"></i>

  <div class="header">
    <%=noticeEntity.getTitle()%>
  </div>
  <div class="content">
    <%=noticeEntity.getContent()%>
  </div>
  <a class="ui label attached bottom right blue" href="<%=noticeEntity.getOrgUrl()%>">原文</a>

</div>


