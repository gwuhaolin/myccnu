<%@ page import="life.lose.ManageLose" %>
<%--链接--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="four fluid ui buttons" style="position: fixed;bottom: 0;z-index: 50;margin: 0">
	<div class="ui button" style="background-color: red">
        <a href="list_lose.jsp">大 家 掉 了</a>
	</div>
	<div class="ui button" style="background-color: red">
        <a href="addLose.jsp">我 掉 了</a>
	</div>
    <div class="ui button" style="background-color: forestgreen">
        <a href="addUpdate.jsp">我 捡 到</a>
    </div>
    <div class="ui button" style="background-color: forestgreen">
        <a href="list_update.jsp">大 家 捡 到</a>
    </div>
</div>
<%--搜索--%>
<form action="searchResult.jsp" method="post" name="search" style="margin: 0;position: fixed;top: 0;opacity: 0.8">
	<div class="ui fluid action input">
		<input type="text" name="want" placeholder="先搜索看有没有同学找到你的东西!">
		<div class="ui button" onclick="document.search.submit()">
			提交
		</div>
	</div>
</form>
<script>
	//设置该失误记录为完成
	function completeOne(btn){
		var id=$(btn).parent().attr('id');
		$.ajax({
			url: "CompleteOneServlet.jsp",
			data: {id:id},
			contentType: "application/x-www-form-urlencoded; charset=utf-8"
		}).done(function (data) {
			$(btn).text(data);
		});
	}
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
	});
	var isIphone=navigator.userAgent.toLowerCase().match(/iphone/i);
	function toCall(btn){
		var phoneNumber=btn.innerText;
		if(isIphone){
			window.location.href="callto:"+phoneNumber;
		}
		else{
			window.location.href="wtai://wp/mc;"+phoneNumber;
		}
	}

</script>