<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 3/3/14
  Time: 9:31 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="/lib/css/bootstrap.min.css" rel="stylesheet">
	<script src="/lib/js/jquery-2.0.2.min.js"></script>
	<script src="/lib/js/bootstrap.min.js"></script>
	<link href="/lib/css/main.css" rel='stylesheet'>
	<script src="/lib/js/main.js"></script>
	<title>失物招领</title>
	<script>
		//加载评论框
		function addComment(the) {
			var id = $(the).attr('id');
			var url = 'comment.jsp?id=' + id;
			var Commnet = $(the).children('.comment').first();
			$(Commnet).attr('src', url);
			$(Commnet).toggle();
		}

		//设置该失误记录为完成
		function completeOne(btn){
			var id=$(btn).parent().parent().attr('id');
			$.ajax({
				url: "CompleteOneServlet.jsp",
				data: {id:id},
				contentType: "application/x-www-form-urlencoded; charset=utf-8"
			}).done(function (data) {
				$(btn).text(data);
			});
		}
	</script>
</head>
<body>
<div class="container">
	<br>
	<a href="addLose.jsp">
		<img src="img/lose.png" class="img-responsive center-block">
	</a>
	<br>
	<a href="addUpdate.jsp">
		<img src="img/update.png" class="img-responsive center-block">
	</a>

	<%--用户的失误招领信息--%>
	<jsp:include page="GetByXHForAJAX.jsp"/>
	<br><br>
</div>

<%--链接--%>
<%@ include file="link.jsp"%>

</body>
</html>