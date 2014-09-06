<%--链接--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="btn-group  btn-group-justified" style="position: fixed;bottom: 0;z-index: 100;margin: 0;opacity: 0.8">
	<a href="list_lose.jsp" class="btn btn-default btn-primary">大家掉了</a>
	<a href="addLose.jsp" class="btn btn-default btn-danger">我掉了</a>
	<a href="addUpdate.jsp" class="btn btn-default btn-info">我捡到</a>
	<a href="list_update.jsp" class="btn btn-default btn-warning">大家捡到</a>
</div>

<script>
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
	});
</script>