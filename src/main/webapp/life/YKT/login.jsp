<%@ page import="tool.Tool" %>
<%--绑定帐号密码登入界面--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 5:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
    <title>绑定帐号密码</title>
</head>
<body>
<div class="ui one column page grid center aligned">
    <%
        String xh = Tool.getXHMMfromCookie(request)[0];
        if (xh == null) {
            xh = "";
        }
        String info = request.getParameter("info");
        if (info == null) {
            info = "Who you are?";
        }
    %>
    <div class="column">
        <div class="ui segment red raised"><%=info%>
        </div>
    </div>

    <div class="column">

        <form id="form" class="ui form" action="bind.jsp" method="post">
            <div class="ui two fields">
                <div class="ui field">
                    <input type="text" name="XH" placeholder="你的学号" value="<%=xh%>">
                </div>
                <div class="ui field">
                    <input type="password" name="YKTMM" placeholder="你的一卡通圈存机密码">
                </div>
            </div>
            <div class="ui field">
                <input type="submit" class="ui button positive fluid" value="GO">
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function () {
        $('#form').form({
            XH: {
                identifier: 'XH',
                rules: [
                    {
                        type: 'empty'
                    }
                ]
            },
            MM: {
                identifier: 'MM',
                rules: [
                    {
                        type: 'empty'
                    }
                ]
            }
        });
    });
</script>
<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>
</body>
</html>
