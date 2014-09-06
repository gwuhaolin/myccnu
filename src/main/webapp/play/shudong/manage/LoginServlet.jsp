<%@ page import="play.shudong.ManageShuDong" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:32 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String password=request.getParameter("password");
	HttpSession httpSession=request.getSession();
	boolean isOk= ManageShuDong.passwordIsOk(password);
	if (isOk){
		httpSession.setAttribute("isOk",isOk);
		response.sendRedirect("manage.jsp");return;
	}else {
		response.sendRedirect("login.jsp?info=Password-not-true!");return;
	}
%>