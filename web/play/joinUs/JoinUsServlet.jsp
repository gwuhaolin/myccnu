<%@ page import="play.joinUs.ManageJoinUs" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/2/14
  Time: 7:21 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String XH=request.getParameter("XH");
	ManageJoinUs.join(XH);
	response.sendRedirect("feedback.jsp?XH="+XH);return;
%>