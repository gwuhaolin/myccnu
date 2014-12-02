<%--用考号去全国查询网站抓取--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/19/14
  Time: 9:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="../../favicon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link type="text/css" href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link type="text/css" href="../../lib/css/main.css">
    <title>CET查询</title>
</head>
<body>
<div class="ui one column page center aligned grid">

    <div class="column">
        <div class="ui huge header icon center aligned">
            <i class="icon circular">?</i>
        </div>
    </div>

    <div class="column">

        <form id="form" class="ui form" action="index.jsp?type=KH" method="post">
            <div class="ui two fields">
                <div class="ui field">
                    <input type="text" maxlength="20" name="kh" placeholder="输入你的考号">
                </div>
                <div class="ui field">
                    <input type="text" maxlength="8" name="name" placeholder="输入你的姓名">
                </div>
            </div>
            <div class="ui field">
                <input type="submit" value="GO" class="ui button positive fluid">
            </div>
        </form>
        <script>
            $(document).ready(function(){
                $('#form').form({
                    kh: {
                        identifier: 'kh',
                        rules: [
                            {
                                type: 'empty'
                            }
                        ]
                    },
                    name: {
                        identifier: 'name',
                        rules: [
                            {
                                type: 'empty'
                            }
                        ]
                    }
                });
            });
        </script>
    </div>

    <div class="column">
        <a class="ui button mini" href="http://cet.99sushe.com/faq/#find"><em>忘记考号?</em></a>
        <a class="ui button mini" href="queryByIdNumber.jsp"><em>使用身份证查询</em></a>
    </div>

</div>
<script src="../../lib/js/main.js"></script>
<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
</body>
</html>
