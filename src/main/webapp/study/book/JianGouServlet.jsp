<%@ page import="study.book.MyLib" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/28/14
  Time: 12:06 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<%
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  String name = request.getParameter("name");
  String press = request.getParameter("press");
  String lan = request.getParameter("lan");
  String XHMM[] = Tool.getXHMMfromCookie(request);
  if (Tool.XHMMisOK(XHMM)) {
    if (MyLib.JianGouBook(name, press, lan, XHMM[0], XHMM[1])) {
      Tool.jspWriteJSForHTML(response, "alert('荐购成功，祝贺')", "window.location='index.jsp'");
      return;
    } else {
      Tool.jspWriteJSForHTML(response, "alert('荐购失败！我出现了点问题~重新试试吧!')", "history.go(-1)");
      return;
    }
  } else {//去绑定
    Tool.jspWriteJSForHTML(response, "shouldBind()", "history.go(-1)");
    return;
  }
%>
