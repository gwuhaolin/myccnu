<%@ page import="tool.Tool" %>
<%@ page import="life.lose.ManageLose" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 8:13 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String des=request.getParameter("des");
	String location=request.getParameter("location");
	String phone=request.getParameter("phone");
	byte type=Byte.parseByte(request.getParameter("type"));
	String XH = Tool.getXHMMfromCookie(request)[0];
	ManageLose.add(des,location,phone,type,XH);
	if (type==ManageLose.TYPE_Lose){
		response.sendRedirect("list_lose.jsp");return;
	}else if (type==ManageLose.TYPE_Update){
		response.sendRedirect("list_update.jsp");return;
	}
%>