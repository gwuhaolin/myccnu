<%@ page import="life.lose.ManageLose" %>
<%--链接--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="four fluid ui buttons mini" style="position: fixed;bottom: 0;z-index: 50;margin: 0">
    <div class="ui button black" onclick="window.location.href='list_lose.jsp'">
        大家掉了
    </div>
    <div class="ui button green" onclick="window.location.href='addLose.jsp'">
        我遗失了
    </div>
    <div class="ui button red" onclick="window.location.href='addUpdate.jsp'">
        我捡到了
    </div>
    <div class="ui button blue" onclick="window.location.href='list_update.jsp'">
        大家捡到
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
