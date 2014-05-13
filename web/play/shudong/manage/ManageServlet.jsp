<%--管理树洞Servlet--%>
<%@ page import="play.shudong.ManageShuDong" %>
<%@ page import="play.shudong.MyShuDongEntity" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/24/14
  Time: 7:04 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

//		验证身份
		Object isOk=session.getAttribute("isOk");
		if (isOk==null || !(Boolean)isOk){
			response.sendRedirect("login.jsp?info=Password-not-true!");
			return;
		}

		String cmd=request.getParameter("cmd");
		int id=Integer.parseInt(request.getParameter("id"));
		String content=request.getParameter("content");
		String date=request.getParameter("date");

		MyShuDongEntity shuDongEntity=new MyShuDongEntity();
		shuDongEntity.setContent(content);
		shuDongEntity.setDate(date);
		shuDongEntity.setId(id);

		if (cmd.equals(ManageShuDong.CMD_Change)){//修改树洞
			int seeCount=Integer.parseInt(request.getParameter("seeCount"));
			shuDongEntity.setSeeCount(seeCount);
			ManageShuDong.change(shuDongEntity);
		}else if (cmd.equals(ManageShuDong.CMD_Delete)){//删除树洞
			ManageShuDong.delete(id);
		}
//		else if (cmd.equals(ManageShuDong.CMD_Add)){//添加树洞
//			ManageShuDong.add(shuDongEntity);
//		}
	}catch (Exception e){
	}
	response.sendRedirect("manage.jsp");
	return;
%>