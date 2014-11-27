<%--续借图书--%>
<%@ page import="study.book.MyLib" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-1-29
  Time: 下午1:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bar_code = request.getParameter("bar_code");
    String check = request.getParameter("check");
    String time = request.getParameter("time");
    String cookie = request.getParameter(MyLib.CookieName);
    String result = MyLib.renew(bar_code, check, time, cookie);
%>
<%=result%>
