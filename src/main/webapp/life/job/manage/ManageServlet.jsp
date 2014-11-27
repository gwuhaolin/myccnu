<%@ page import="life.jobs.ManageJob" %>

<%@ page import="life.jobs.MyJobEntity" %>

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
        String jobInfo = request.getParameter("jobInfo");
        String jobTime = request.getParameter("jobTime");
        String jobLocation = request.getParameter("jobLocation");
        String money = request.getParameter("money");
        String manager = request.getParameter("manager");
        String otherInfo = request.getParameter("otherInfo");


        if (cmd.equals(ManageJob.CMD_Add)) {//添加
            MyJobEntity jobEntity = new MyJobEntity(name, jobInfo, money, jobTime, jobLocation, otherInfo, manager, target);
            ManageJob.add(jobEntity);
        } else if (cmd.equals(ManageJob.CMD_Change)) {//修改
            MyJobEntity jobEntity = new MyJobEntity(name, jobInfo, money, jobTime, jobLocation, otherInfo, manager, target);
            jobEntity.setId(id);
            ManageJob.change(jobEntity);
        } else if (cmd.equals(ManageJob.CMD_Delete)) {//删除
            ManageJob.delete(id);
        }

        if (target == ManageJob.TARGET_PrivateTeacher) {
            response.sendRedirect("manage_PrivateTeacher.jsp");
            return;
        } else if (target == ManageJob.TARGET_PartTimeJob) {
            response.sendRedirect("manage_PartTimeJobs.jsp");
            return;
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("login.jsp");
        return;
    }
%>