<%--AJAX获得电影列表--%>
<%@ page import="play.movie.ManageMovie" %>
<%@ page import="java.util.List" %>
<%@ page import="play.movie.MyMovieEntity" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/22/14
  Time: 10:45 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		int begin = Integer.parseInt(request.getParameter("begin"));
		String target = request.getParameter("target");
		List<MyMovieEntity> movies = ManageMovie.get_page(begin, target);
		for (MyMovieEntity one : movies) {
%>
<div class="column">
	<div class="ui stacked inverted segment">

		<%--电影图片URL--%>
		<%
			String picUrl = one.getPicUrl();
			if (picUrl != null && picUrl.length() > 0) {
		%>
		<div class="ui image labeled rounded fluid">
			<div class="ui label corner icon small red inverted">
				<i class="icon  heart"></i>
			</div>
			<img src="<%=picUrl%>">
		</div>
		<%
			}
		%>
		<div class="ui divider icon horizontal inverted"><i class="icon moon green"></i></div>
		<!--片名-->
		<div class="ui label ribbon teal large"><%=one.getName()%>
		</div>

		<%--电影描述--%>
		<%
			String des = one.getDes();
			if (des != null && des.length() > 0) {
		%>
		<div class="label"><%=des%>
		</div>
		<%
			}
		%>

		<div class="ui relaxed divided mini list">
			<%--电影时间--%>
			<%
				String date = one.getDate();
				if (date != null && date.length() > 0) {
			%>
			<div class="item">
				<i class="time icon circular inverted blue"></i>

				<div class="content">
					<a class="header"><%=date%>
					</a>

					<div class="description">时间
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%--电影费用--%>
			<%
				String pay = one.getPay();
				if (pay != null && pay.length() > 0) {
			%>
			<div class="item">
				<i class="money icon circular inverted black"></i>

				<div class="content">
					<a class="header"><%=pay%>
					</a>

					<div class="description">费用
					</div>
				</div>
			</div>
			<%
				}
			%>

			<%--其它说明--%>
			<%
				String other = one.getOther();
				if (other != null && other.length() > 0) {
			%>
			<div class="item">
				<i class="tags icon circular inverted green"></i>

				<div class="content">
					<a class="header"><%=other%>
					</a>

					<div class="description">其它
					</div>
				</div>
			</div>
			<%
				}
			%>

		</div>


		<%--评论框--%>
		<div class="ui button icon circular inverted blue mini" onclick="toggleComment(this)">
			<i class="icon comment"></i>
		</div>
		<iframe src="comment.jsp?id=<%=one.getId()%>"
		        style="z-index:10;border: none;display: none;opacity: 0.9;position: absolute;width: 100%;left: 0;bottom: -300px;height: 300px;">
		</iframe>
	</div>
</div>
<%
		}
	} catch (Exception e) {
		return;
	}
%>
