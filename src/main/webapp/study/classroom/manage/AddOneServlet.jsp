<%@ page import="study.classroom.ManageClassroom" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/31
  Time: 17:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  String JiHaoLou = request.getParameter("JiHaoLou");
  String XinQiJi = request.getParameter("XinQiJi");
  String DiJiJie = request.getParameter("DiJiJie");
  String Classroom = request.getParameter("Classroom");
  boolean result = ManageClassroom.add(JiHaoLou, XinQiJi, DiJiJie, Classroom);
  response.sendRedirect("add.jsp?info=" + result + '-' + JiHaoLou + "-" + XinQiJi + "-" + DiJiJie + "-" + Classroom);
%>
