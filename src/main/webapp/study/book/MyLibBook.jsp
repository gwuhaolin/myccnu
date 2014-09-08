<%--我的图书馆--%>
<%@ page import="study.book.MyLib" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-1-29
  Time: 下午12:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<link href="../../lib/css/semantic.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
	<title>我的图书馆</title>
</head>
<body>
<%
	boolean passwordIsOk = false;
	List<MyLib.MyLibBook> myLibBooks;
	String XHMM[] = Tool.getXHMMfromCookie(request);
	String tempMM = request.getParameter("MM");
	if (tempMM != null) {
		XHMM[1] = tempMM;
	}
	if (Tool.XHMMisOK(XHMM)) {
		myLibBooks = MyLib.get(XHMM[0], XHMM[1]);
		if (myLibBooks == null) {
			passwordIsOk = MyLib.passwordIsOk(XHMM[0], XHMM[1]);
		}
	} else {//去绑定
		Tool.jspWriteJSForHTML_shouldBind(response, "");
		return;
	}
%>
<div class="ui three column stackable page grid">

	<%--判断密码是否为初始密码,并反馈--%>
	<%
		if (XHMM[1].equals("123456")) {
	%>
	<div class="ui message warning fluid column">我发现了你的密码为初始密码123456!赶快<a href="http://202.114.34.15/reader/login.php">点击这里</a>登入后修改密码!(绿色的修改密码按钮)
	</div>
	<%
		}
	%>

	<%--已经借图书列表--%>
	<%
		if (myLibBooks != null) {//密码正确而且接了书
	%>
	<%
		for (int i = 1; i < myLibBooks.size(); i++) {
			MyLib.MyLibBook one = myLibBooks.get(i);
	%>
	<input type="hidden" id="cookie" value="<%=myLibBooks.get(0).getTitle()%>">

	<div class="column">

		<div class="ui stacked segment">
			<div class="ui horizontal icon divider"><i class="icon book"></i></div>
			<div class="ui ribbon label black huge"><%=one.getTitle()%>
			</div>
			<div class="ui relaxed divided mini list">

				<div class="item">
					<i class="play icon circular inverted green"></i>

					<div class="content">
						<a class="header"><%=one.getGetTime()%>
						</a>

						<div class="description">起始日期
						</div>
					</div>
				</div>

				<div class="item">
					<i class="stop icon circular inverted red"></i>

					<div class="content">
						<a class="header"><%=one.getBackTime()%>
						</a>

						<div class="description">应还日期
						</div>
					</div>
				</div>

			</div>

			<%--续借--%>
			<button class="ui button circular inverted icon teal" id="<%=one.getIndex()%>"
			        onclick="<%=one.getXJJavaScriptFunction()%>">
				<i class="icon refresh"></i>续借
			</button>

			<a href="<%=one.getBookInfoURL()%>">
				<div class="ui label attached right bottom small green"><i class="icon tags"> 详细信息</i></div>
			</a>
		</div>
	</div>
	<%
		}
	%>
	<%
	} else if (passwordIsOk) {//没有借书
	%>
	<script>
		alert('你目前还没有在接图书哦！也有可能是网络繁忙，等下再试试呗！');
		window.location = 'index.jsp';
	</script>
	<%
		} else if (!XHMM[1].equals("123456")) {//试试123456可以不?
			response.sendRedirect("MyLibBook.jsp?MM=123456");
			return;
		} else if (XHMM[1].equals("123456")) {//123456不对,你告诉我的也不对,那就是你真的不对了!!
			Tool.jspWriteJSForHTML_shouldBind(response, "密码错误!");
			return;
		}
	%>

</div>

<script>
	<%--续借--%>
	function getInLib(barcode, check, num) {
		var cookie = $('#cookie').attr('value');
		var btn = $("#" + num);
		$(btn).addClass('loading');
		$(btn).load('RenewBookServlet.jsp?bar_code=' + barcode + "&check=" + check + "&time=" + new Date().getTime() + "&PHPSESSID=" + cookie);
		$(btn).removeClass('loading');
	}
</script>

<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>

</body>
</html>