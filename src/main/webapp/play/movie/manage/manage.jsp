<%@ page import="play.movie.ManageMovie" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 14-2-3
  Time: 下午1:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpSession = request.getSession();
    boolean isOk = (Boolean) httpSession.getAttribute("ManagePasswordIsOk");
    if (!isOk) {
        response.sendRedirect("login.jsp?info=Password-not-true!");
        return;
    }
    String target = (String) httpSession.getAttribute("target");
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../../../lib/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../../lib/js/jquery.min.js"></script>
    <script src="../../../lib/js/bootstrap.min.js"></script>
    <link href="../../../lib/css/main.css" rel='stylesheet'>
    <script src="../../../lib/js/main.js"></script>
    <title>管理电影</title>

</head>
<body>
<div class="container">
    <a class="form-control btn-info text-center" href="../list.jsp?target=<%=target%>"
       target="_blank">预览效果</a>
    <br>

    <%--用于添加新的电影--%>
    <div class="panel panel-warning">
        <form method="post" action="ManageServlet.jsp">
            <input type="hidden" name="cmd" value="<%=ManageMovie.Cmd_add%>">

            <div class="panel-heading text-center" style="padding: 0">
                <div class="input-group">
                    <span class="input-group-addon">影片名</span>
                    <input type="text" class="form-control" name="name" placeholder="添加新的电影的名称">
                </div>
            </div>

            <img src="" class="img-responsive img-thumbnail img-rounded center-block">

            <div class="input-group">
                <span class="input-group-addon">图片</span>
                <input type="text" onchange="reloadPic(this)" class="form-control" name="picUrl" value="">
            </div>

            <div class="input-group">
                <span class="input-group-addon">简介</span>
                <textarea class="form-control input-lg" name="des"></textarea>
            </div>

            <div class="input-group">
                <span class="input-group-addon">时间</span>
                <input type="text" class="form-control" name="date" value="" placeholder="电影放映时间">
            </div>

            <div class="input-group">
                <span class="input-group-addon">费用</span>
                <input type="text" class="form-control" name="pay" value="10元">
            </div>

            <div class="input-group">
                <span class="input-group-addon">其它</span>
                <input type="text" class="form-control" name="other" value="" placeholder="其它备注，没有可以不写">
            </div>

            <input type="submit" class="form-control btn-warning" value="添加新的电影">
        </form>
    </div>

    <%--展示修改已有电影--%>
    <jsp:include page="GetMovieManageFormForAJAX.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="target" value="<%=target%>"/>
    </jsp:include>

    <%--ajax 加载更多--%>
    <button class="form-control btn-info input-lg" onclick="ajaxMore(this)">更多</button>
    <br>
</div>
<script>
    <%
    Map<String,Object> params=new HashMap<>(1);
    params.put("target",target);
    %>
    <%=Tool.makeAJAXLoadMoreJS("GetMovieManageFormForAJAX.jsp",params)%>

    //重新加载图片
    function reloadPic(input) {
        var img = $(input).parent().prev('img');
        $(img).attr('src', input.value);
    }

</script>
</body>
</html>
