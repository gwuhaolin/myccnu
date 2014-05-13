<%@ page import="tool.Tool" %>
<%@ page import="life.lose.ManageLose" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	int id = Integer.parseInt(request.getParameter("id"));
	String XH = Tool.getXHMMfromCookie(request)[0];
	String re = ManageLose.complete(id, XH);
%>
<%=re%>