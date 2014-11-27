<%@ page import="play.movie.ManageMovie" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:32 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String target = request.getParameter("target");
    String password = request.getParameter("password");
    HttpSession httpSession = request.getSession();
    boolean isOk = ManageMovie.ManagePasswordIsOk(password, target);
    if (isOk) {
        httpSession.setAttribute("ManagePasswordIsOk", isOk);
        httpSession.setAttribute("target", target);
        response.sendRedirect("manage.jsp");
    } else {
        response.sendRedirect("login.jsp?info=Password-not-true!");
    }
%>