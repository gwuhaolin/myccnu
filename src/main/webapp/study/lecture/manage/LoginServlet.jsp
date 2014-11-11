<%@ page import="study.lecture.ManageEvent" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:32 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String password = request.getParameter("password");
  HttpSession httpSession = request.getSession();
  boolean isOk = ManageEvent.ManagePasswordIsOK(password);
  int target = Integer.parseInt(request.getParameter("target"));
  if (isOk) {
    httpSession.setAttribute("isOk", isOk);
    if (target == ManageEvent.TARGET_Lecture) {
      response.sendRedirect("manage_Lecture.jsp");
      return;
    } else if (target == ManageEvent.TARGET_School) {
      response.sendRedirect("manage_School.jsp");
      return;
    }
  } else {
    response.sendRedirect("login.jsp?info=Password-not-true!");
    return;
  }
%>
