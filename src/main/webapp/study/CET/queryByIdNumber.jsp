<%--由于数据库里没有该同学的信息就直接用身份证号去学校的网站抓取--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 9:25 PM
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

        <form id="form" class="ui form" action="index.jsp?type=ID" method="post">
            <div class="ui field">
                <input type="text" maxlength="20" name="id"
                       placeholder="你的身份证号码">
            </div>
            <div class="inline fields">

                <div class="field">
                    <label>
                        <input type="radio" name="grade" value="4" checked>CET4
                    </label>
                </div>
                <div class="field">
                    <label>
                        <input type="radio" name="grade" value="6" checked>CET6
                    </label>
                </div>
            </div>

            <div class="ui field">
                <input type="submit" value="GO" class="ui button positive fluid">
            </div>
        </form>
        <script>
            $(document).ready(function(){
                $('#form').form({
                    id: {
                        identifier: 'id',
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
        <a class="ui button mini" href="queryByKH.jsp"><em>使用考号查询</em></a>
    </div>
</div>
<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>
</body>
</html>
