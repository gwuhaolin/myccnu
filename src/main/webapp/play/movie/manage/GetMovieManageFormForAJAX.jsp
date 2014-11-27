<%@ page import="java.util.List" %>
<%@ page import="play.movie.MyMovieEntity" %>
<%@ page import="play.movie.ManageMovie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    try {
        int begin = Integer.parseInt(request.getParameter("begin"));
        String target = request.getParameter("target");
        List<MyMovieEntity> moives = ManageMovie.get_page(begin, target);//按照发布时间获得树洞
        for (MyMovieEntity one : moives) {
%>
<div class="panel panel-info">
    <%--修改电影--%>
    <form method="post" action="ManageServlet.jsp">
        <div class="panel-heading text-center" style="padding: 0">
            <%--电影标题--%>
            <div class="input-group">
                <span class="input-group-addon"><%=one.getId()%></span>
                <span class="input-group-addon">影片名</span>
                <input type="text" class="form-control" name="name" value="<%=one.getName()%>">
            </div>
        </div>

        <%--电影图片URL--%>

        <img src="<%=one.getPicUrl()%>" class="img-responsive img-thumbnail img-rounded center-block">

        <%--电影图片URL修改框--%>
        <div class="input-group">
            <span class="input-group-addon">图片</span>
            <input type="text" onchange="reloadPic(this)" class="form-control" name="picUrl"
                   value="<%=one.getPicUrl()%>" placeholder="填入图片的URL">
        </div>

        <%--电影描述--%>
        <div class="input-group">
            <span class="input-group-addon">简介</span>
      <textarea class="form-control input-lg" name="des"><%=one.getDes()%>
      </textarea>
        </div>

        <%--电影时间--%>
        <div class="input-group">
            <span class="input-group-addon">时间</span>
            <input type="text" class="form-control" name="date" value="<%=one.getDate()%>">
        </div>

        <%--电影费用--%>
        <div class="input-group">
            <span class="input-group-addon">费用</span>
            <input type="text" class="form-control" name="pay" value="<%=one.getPay()%>">
        </div>

        <%--其它说明--%>
        <div class="input-group">
            <span class="input-group-addon">其它</span>
            <input type="text" class="form-control" name="other" value="<%=one.getOther()%>">
        </div>

        <input type="hidden" name="cmd" value="<%=ManageMovie.Cmd_change%>">

        <input type="hidden" class="btn active id" value="<%=one.getId()%>" name="movieId">

        <%--提交修改--%>
        <input type="submit" class="form-control btn-primary " value="修改">

    </form>
    <%--删除电影--%>
    <form action="ManageServlet.jsp" method="post">
        <input type="hidden" class="btn active id" value="<%=one.getId()%>" name="movieId">
        <input type="hidden" name="cmd" value="<%=ManageMovie.Cmd_delete%>">
        <input type="submit" class="form-control btn-danger" value="删除">
    </form>
</div>
<%
        }
    } catch (Exception e) {
        return;
    }
%>
