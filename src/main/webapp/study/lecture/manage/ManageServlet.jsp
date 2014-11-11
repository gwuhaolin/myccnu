<%@ page import="study.lecture.ManageEvent" %>
<%@ page import="study.lecture.MyEventEntity" %>

<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:34 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  try {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    Object isOk = session.getAttribute("isOk");
    if (isOk == null || !(Boolean) isOk) {
      response.sendRedirect("login.jsp?info=Password-not-true!");
      return;
    }
    String cmd = request.getParameter("cmd");
    int id = Integer.parseInt(request.getParameter("id"));
    int target = Integer.parseInt(request.getParameter("target"));

    String name = request.getParameter("name");
    String intro = request.getParameter("intro");
    String runDate = request.getParameter("runDate");
    String runLocation = request.getParameter("runLocation");
    String manager = request.getParameter("manager");
    String otherInfo = request.getParameter("otherInfo");


    if (cmd.equals(ManageEvent.CMD_Add)) {//添加
      MyEventEntity jobEntity = new MyEventEntity(name, manager, runDate, runLocation, intro, otherInfo, target);
      ManageEvent.add(jobEntity);
    } else if (cmd.equals(ManageEvent.CMD_Change)) {//修改
      MyEventEntity jobEntity = new MyEventEntity(name, manager, runDate, runLocation, intro, otherInfo, target);
      jobEntity.setId(id);
      ManageEvent.change(jobEntity);
    } else if (cmd.equals(ManageEvent.CMD_Delete)) {//删除
      ManageEvent.delete(id);
    }

    if (target == ManageEvent.TARGET_School) {
      response.sendRedirect("manage_School.jsp");
      return;
    } else if (target == ManageEvent.TARGET_Lecture) {
      response.sendRedirect("manage_Lecture.jsp");
      return;
    }
  } catch (Exception e) {
    e.printStackTrace();
    response.sendRedirect("login.jsp");
    return;
  }
%>
