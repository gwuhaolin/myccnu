<%@ page import="tool.CCNUPortal" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/14/14
  Time: 1:51 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String XH=request.getParameter("XH");
	String MM=request.getParameter("MM");
	if (CCNUPortal.XHMMisTrue(XH, MM)){//密码正确
		Tool.setXHMMtoCookies(response, XH, MM);//保存帐号密码到cookies
		Tool.setXHMMtoSQL(XH, MM);//保存帐号密码到数据库
		Tool.jspWriteJSForAJAX(response, "executeAJAXBindSuccess()");return;
	}else {//密码错误
		Tool.jspWriteJSForAJAX(response, "executeAJAXBindFill()");return;
	}
%>