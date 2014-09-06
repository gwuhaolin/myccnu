<%@ page import="life.notice.ManageNotice" %>
<%@ page import="life.notice.MyNoticeEntity" %>
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
		Object isOk=session.getAttribute("isOk");
		if (isOk==null || !(Boolean)isOk){
			response.sendRedirect("login.jsp?info=Password-not-true!");
			return;
		}
		String cmd=request.getParameter("cmd");
		int id=Integer.parseInt(request.getParameter("id"));

		String title=request.getParameter("title");
		String date=request.getParameter("date");
		String fromSite=request.getParameter("fromSite");
		String orgUrl=request.getParameter("orgUrl");
		String content=request.getParameter("content");

		if (cmd.equals(ManageNotice.CMD_Add)){//添加
			MyNoticeEntity noticeEntity=new MyNoticeEntity(title,date,orgUrl,content,fromSite);
			ManageNotice.add_NotSame(noticeEntity);
		}else if (cmd.equals(ManageNotice.CMD_Change)){//修改
			MyNoticeEntity noticeEntity=new MyNoticeEntity(title,date,orgUrl,content,fromSite);
			noticeEntity.setId(id);
			ManageNotice.changeNotice(noticeEntity);
		}else if (cmd.equals(ManageNotice.CMD_Delete)){//删除
			ManageNotice.remove(id);
		}
		response.sendRedirect("manage.jsp");
		return;
	}catch (Exception e){
		e.printStackTrace();
		response.sendRedirect("login.jsp");
		return;
	}
%>