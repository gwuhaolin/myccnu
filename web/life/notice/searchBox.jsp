<%@ page import="life.notice.ManageNotice" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/28
  Time: 11:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--隐藏于左边的功能框--%>
<div>
	<%--隐藏于左边的搜索框--%>
	<div class="ui styled sidebar floating">
		<div class="ui header center aligned icon">
			<i class="icon circular search blue inverted"></i>
		</div>
		<form class="ui form" method="post" action="search.jsp">

			<div class="field">
				<div class="ui compact menu fluid">
					<div class="ui simple dropdown item fluid">
						你想要来自哪里的呢 <i class="dropdown icon"></i>

						<div class="menu fluid">
							<%
								for (String one[] : ManageNotice.FromSiteName) {
							%>
							<a class="item" href="search.jsp?want=<%=one[1]%>"><%=one[0]%>
							</a>
							<%
								}
							%>
						</div>
					</div>
				</div>
			</div>

			<div class="field">
				<div class="ui icon input">
					<i class="search icon"></i>
					<input type="text" name="want" placeholder="输入关键字搜索">
				</div>
			</div>

			<div class="field">
				<input type="submit" class="ui black fluid button"/>
			</div>
			<div class="ui error message"></div>

		</form>
	</div>

	<%--打开隐藏于左边的搜索框的按钮--%>
	<i class="ui add sign icon link big" onclick="showSearchBox(this)" style="position: fixed;bottom: 5px;right: 5px"></i>

	<script>
		function showSearchBox(btn) {
			$('.sidebar').first().sidebar({
				overlay: true
			}).sidebar('toggle');
			$(btn).toggleClass('red');
			$(btn).toggleClass('add');
			$(btn).toggleClass('remove ');
		}

		$('.ui.form')
				.form({
					want: {
						identifier: 'want',
						rules: [
							{
								type: 'empty',
								prompt: '你想要什么呢?输入关键字吧~'
							}
						]
					}
				});
	</script>
</div>