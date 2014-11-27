<%--用户提交他喜欢的电影--%>
<%@ page import="play.movie.ManageMovie" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 8:48 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getSession().setAttribute("target", ManageMovie.Target_iWANT);
    request.getSession().setAttribute("ManagePasswordIsOk", true);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <script src="../../lib/js/jquery.min.js"></script>
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <script src="../../lib/js/semantic.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
    <script src="../../lib/js/main.js"></script>
    <title>分享你喜爱的电影</title>
</head>
<body>
<div class="ui one column page grid">
    <div class="column">
        <%--头--%>
        <div class="ui header icon center aligned">
            <i class="icon heart red inverted circular"></i>

            <div class="content">分享你喜爱的电影
                <div class="sub header">告诉我们你希望与大家一起分享的电影,我们会告诉露天电影场和华大青年剧场哦!他们会尽可能实现你的愿望.
                </div>
            </div>

        </div>
        <%--表单--%>
        <form class="ui form" method="post" action="manage/ManageServlet.jsp">
            <input type="hidden" name="cmd" value="<%=ManageMovie.Cmd_add%>">
            <input type="hidden" name="target" value="<%=ManageMovie.Target_iWANT%>">

            <div class="field fluid">
                <input type="text" name="name" placeholder="电影叫什么名字?">
            </div>
            <div class="field  fluid">
                <input type="text" name="date" value="" placeholder="你希望电影什么时候放映?">
            </div>
            <div class="field  fluid">
                <textarea name="des" placeholder="电影的大概是讲述什么?"></textarea>
            </div>
            <div class="field  fluid">
                <input type="text" name="other" value="" placeholder="其它备注，没有可以不写">
            </div>
            <div class="field  fluid">
                <input type="submit" class="ui button fluid teal" value="提交申请">
            </div>
            <div class="ui error message"></div>
        </form>
    </div>
</div>

<script>
    $('.ui.form')
            .form({
                username: {
                    identifier: 'name',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '电影叫什么名字?'
                        }
                    ]
                }
            });
</script>

</body>
</html>
