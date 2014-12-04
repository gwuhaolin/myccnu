<%@ page import="life.YKT.ManageYKT" %>
<%@ page import="tool.ValidateException" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/2/14
  Time: 10:37 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String XH = request.getParameter("XH");
    String MM = request.getParameter("YKTMM");
    try {
        ManageYKT.spiderAndGet(XH, MM);
        ManageYKT.setXHMMtoCookies(response, XH, MM);//保存帐号密码到cookies
        //登陆成功,为他查询,然后跳转到消费列表
        response.sendRedirect("list.jsp?type=" + ManageYKT.Type_Detail);
        return;
    } catch (ValidateException e) {//密码错误
        response.sendRedirect("login.jsp?info=Password-error!");
        return;
    }
%>