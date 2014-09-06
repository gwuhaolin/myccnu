<%--通知列表里获得AJAX通知--%>
<%@ page import="java.util.List" %>
<%@ page import="life.lose.MyLoseEntity" %>
<%@ page import="life.lose.ManageLose" %>
<%
	// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	try {
		int begin = Integer.parseInt(request.getParameter("begin"));
		byte type = Byte.parseByte(request.getParameter("type"));
		List<MyLoseEntity> loseEntities = ManageLose.get_page(begin, type);
		for (MyLoseEntity one : loseEntities) {
%>
<div class="thumbnail alert-warning" id="<%=one.getId()%>" onclick="addComment(this)">
	<%--描述--%>
	<h4><%=one.getMyDes()%>
	</h4>
	<hr>
	<%--发布时间--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-time"></i></button>
		<button class="btn active"><%=one.getMyDate()%>
		</button>
	</div>
	<%--失物地点--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-map-marker"></i></button>
		<button class="btn active"><%=one.getMyLocation()%>
		</button>
	</div>
	<%--联系方式--%>
	<div class="btn-group btn-group-xs">
		<button class="btn btn-info active"><i class="glyphicon glyphicon-phone"></i></button>
		<button class="btn active"><%=one.getMyPhone()%>
		</button>
	</div>
	<%--目前状态--%>
	<div class="btn-group btn-group-xs">
		<button class="btn active"><i class="glyphicon glyphicon-stats"></i></button>
		<button onclick="completeOne(this);" class="btn <%=one.bootstapStateString()%>"><%=one.stateString()%>
		</button>
	</div>
	<%--失物还是招领--%>
	<div class="btn-group btn-group-xs">
		<button class="btn active btn-info"><i class="glyphicon <%=one.loseOrUpdateGl()%>"></i></button>
		<button class="btn active"><%=one.loseOrUpdateChinese()%>
		</button>
	</div>
	<%--是否是今天的--%>
	<%
		if (one.today()) {
	%>
	<button class="btn btn-success btn-xs active">Today</button>
	<%
		}
	%>
	<%--评论框--%>
	<iframe src="" class="form-control comment" style="padding: 0;border: none;height: 400px;display: none"></iframe>

</div>
<%
		}
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
%>