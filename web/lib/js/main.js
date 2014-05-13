//加载时间很长是点击按钮后按钮会变样,并不能用
function loading(btn) {
	$(btn).text('正在努力加载中.....').addClass('disabled');
}

//返回上一页
function back() {
	history.back();
}

//高亮显示关键字
function showHighLight(key) {
	$.getScript('/lib/js/jquery.highlight.js', function () {
		$('body').highlight(key);
	});
}

//////////////for New////////////////////

//还没有绑定,需要绑定
function shouldBind(msg) {
	if (msg == null || msg.length < 2) {
		msg = '需要验证你的身份';
	}
	var bindModal = $('#modal_bind');
	var newBindModal = $('<div class="ui basic modal hidden" id="modal_bind"><i class="close icon red circular inverted"></i><div class="ui header icon center aligned"><i class="icon user inverted red circular" id="icon_bind"></i><em id="msg_bind"></em></div><div class="ui form"><div class="two fields"><div class="field"><div class="ui icon input"><input type="text"  value="" id="XH_bind" placeholder="你的学号"><i class="user icon"></i></div></div><div class="field"><div class="ui icon input"><input type="password"  value="" id="MM_bind" placeholder="你的信息门户登入密码"><i class="lock icon"></i></div></div></div><div class="field"><div id="btn_bind" class="ui button blue" onclick="executeAJAXBind()">GO</div></div></div></div>');
	$(newBindModal).find('#msg_bind').text(msg);
	if (bindModal.length == 0) {
		bindModal = newBindModal;
		$(document.body).append(bindModal);
	} else {
		$(bindModal).html($(newBindModal).html());
	}
	$(bindModal).modal('setting', {
		closable: true,
		onDeny: function () {
			window.close();
		}
	}).modal('show');
}


//异步执行绑定
function executeAJAXBind() {
	var bindModal = $('#modal_bind');
	var XH = $($(bindModal).find('#XH_bind')).val();
	var MM = $($(bindModal).find('#MM_bind')).val();
	var msg = $(bindModal).find('#msg_bind');
	if (XH == null || XH.length < 2 || MM == null) {
		$(msg).text('输入的帐号密码正常点好吗!');
	} else {
		$(msg).text('正在努力加载中...');
		$.getScript('../../java/bind/BindServlet.jsp?XH=' + XH + '&MM=' + MM);
	}
}

//异步执行绑定成功后调用
function executeAJAXBindSuccess() {
	alert('验证身份通过,请重新执行你想要的功能');
	location.reload();//刷新当前页面
}

//异步执行绑定失败后调用
function executeAJAXBindFill() {
	var bindModal = $('#modal_bind');
	var msg = $(bindModal).find('#msg_bind');
	$(msg).text('验证失败!帐号密码错误');
}

//评论框打开关闭
function toggleComment(btn) {
	var comment = $(btn).next();
	$(comment).toggle();
}

//关闭微信下方条
function closeWeiXinBtn() {
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
	});
}

//已美观化的alert
function alertMsg(msg) {
	var newBindModal = $('<div class="ui modal"><i class="close icon circular red inverted"></i><div class="header">' + msg + '</div></div>');
	$(document.body).append(newBindModal);

	$(newBindModal).modal('setting', {
		closable: true,
		onHide: function () {
			history.go(-3);
		}
	}).modal('show');
}

/**
 * 动画滚到底部
 */
function goBottom() {
	$('html, body, .content').animate({scrollTop: $(document).height()}, 'slow');
}

/**
 * 动画滚到顶部
 */
function goTop() {
	$('html, body, .content').animate({scrollTop: 0}, 'slow');
}

/**
 * 使用jsonp方法跨域获得JSON时传入JSP部分路径生成完整标准jsonp URL
 * @param path
 * @returns {string}
 */
function makeApiUrl(path){
	/**
	 * 主机基本URL
	 * @type {string}
	 */
	var HostURL="http:/localhost:8080/api/";

	/**
	 * 使用jsonp方法跨域获得JSON的回调函数
	 * @type {string}
	 */
	var CallBackName='?callback=?';

	return HostURL+path+CallBackName;
}

/**
 * 弹出出现了错误Dimmer
 */
function showError() {
	$('<div class="ui page dimmer visible active"><div class="content"><div class="center"><h2 class="ui inverted icon header"><i class="icon circular inverted bug blue"></i>出现点小问题<div class="sub header">如果你的网络没有问题,那我们又要抓虫子了</div></h2></div></div></div>').appendTo(document.body).dimmer('show');
}

/**
 * 解析json字符串返回json对象
 */
function parseJSON(jsonStr) {
	return eval('(' + jsonStr + ')');
}

//当加载完所有的资源后在加载的js
$(document).ready(function () {
	$.getScript('/lib/js/afterDocReady.js');
});

