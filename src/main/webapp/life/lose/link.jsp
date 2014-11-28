<%@ page import="life.lose.ManageLose" %>
<%--链接--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="four fluid ui buttons mini" style="position: fixed;bottom: 0;z-index: 50;margin: 0">
    <a class="ui button black" href="index.jsp?type=<%=ManageLose.TYPE_Lose%>">
        大家掉了
    </a>
    <a class="ui button green" href="addLose.jsp">
        我遗失了
    </a>
    <a class="ui button red" href="addUpdate.jsp">
        我捡到了
    </a>
    <a class="ui button blue" href="index.jsp?type=<%=ManageLose.TYPE_Update%>">
        大家捡到
    </a>
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
