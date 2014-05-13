<%@ page import="tool.studentInfo.MainEntity" %>
<%@ page import="tool.studentInfo.ManageStudentInfo" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/4/14
  Time: 9:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	MainEntity one = ManageStudentInfo.get(request);
	if (one != null) {
%>
<%=one.toString()%>
<img src="/studentImg/<%=one.getStudentId()%>.jpg">
<%
} else {
%>
你是谁!
<%
	}
%>

