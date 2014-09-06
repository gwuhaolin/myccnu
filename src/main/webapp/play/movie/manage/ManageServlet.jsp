<%@ page import="play.movie.ManageMovie" %>
<%@ page import="play.movie.MyMovieEntity" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:34 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	Object isOk=session.getAttribute("ManagePasswordisOk");
	if (isOk==null || !(Boolean)isOk){
		response.sendRedirect("login.jsp?info=Password-not-true!");return;
	}
	String target=(String)session.getAttribute("target");
	String cmd=request.getParameter("cmd");
	String name=request.getParameter("name");
	String picUrl=request.getParameter("picUrl");
	String des=request.getParameter("des");
	String pay=request.getParameter("pay");
	String other=request.getParameter("other");
	String date=request.getParameter("date");

	if (cmd.equals(ManageMovie.Cmd_add)){//添加电影
		MyMovieEntity movieEntity=new MyMovieEntity(name,picUrl,des,pay,other,date,target);
		ManageMovie.add(movieEntity);
	}else if (cmd.equals(ManageMovie.Cmd_change)){//修改电影
		MyMovieEntity movieEntity=new MyMovieEntity(name,picUrl,des,pay,other,date,target);
		int id=Integer.parseInt(request.getParameter("movieId"));
		movieEntity.setId(id);
		ManageMovie.change(movieEntity);
	}else if (cmd.equals(ManageMovie.Cmd_delete)){//删除电影
		int id=Integer.parseInt(request.getParameter("movieId"));
		ManageMovie.remove(id);
	}
	if (target.equals(ManageMovie.Target_iWANT)){//如果是同学们提交想要的电影,那么提交后转到所有的提交结果
		response.sendRedirect("../list.jsp?target=iWANT");return;
	}
	response.sendRedirect("manage.jsp");return;
%>