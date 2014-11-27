<%@ page import="life.jobs.ManageJob" %>
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
    boolean isOk = ManageJob.managePasswordIsOK(password);
    int target = Integer.parseInt(request.getParameter("target"));
    if (isOk) {
        httpSession.setAttribute("isOk", isOk);
        if (target == ManageJob.TARGET_PrivateTeacher) {
            response.sendRedirect("manage_PrivateTeacher.jsp");
            return;
        } else if (target == ManageJob.TARGET_PartTimeJob) {
            response.sendRedirect("manage_PartTimeJobs.jsp");
            return;
        }
    } else {
        response.sendRedirect("login.jsp?info=Password-not-true!");
        return;
    }
%>