<%--用Cookies里的学号查询,去数据库里查询缓存去学校抓取--%>
<%@ page import="study.CET.Cet46Entity" %>
<%@ page import="study.CET.ManageCET" %>
<%@ page import="tool.Tool" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String type = request.getParameter("type");
    if (type == null) {
        type = "XH";
    }
    Cet46Entity cet = null;
    if (type.equals("XH")) {
        String XHMM[] = Tool.getXHMMfromCookie(request);
        if (Tool.XHMMisOK(XHMM)) {
            cet = ManageCET.get(XHMM[0]);
        } else {//去绑定
            Tool.jspWriteJSForHTML_shouldBind(response, "");
            return;
        }
    } else if (type.equals("ID")) {
        String id = request.getParameter("id");
        String grade = request.getParameter("grade");
        cet = ManageCET.scan(grade, id);
    } else if (type.equals("KH")) {
        String kh = request.getParameter("kh");
        String name = request.getParameter("name");
        cet = ManageCET.spider_KH(kh, name);
    }
    request.getSession().setAttribute("cet", cet);
    response.sendRedirect("result.jsp");
%>