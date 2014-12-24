<%--用Cookies里的学号查询,去数据库里查询缓存去学校抓取--%>
<%@ page import="study.CET.Cet46Entity" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/18/14
  Time: 7:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cet46Entity cet = (Cet46Entity) request.getSession().getAttribute("cet");
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <link rel="icon" href="../../favicon.ico">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link type="text/css" href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link type="text/css" href="../../lib/css/main.css" rel="stylesheet">
    <style>
        span {
            font-weight: bolder;
            color: #000000;
        }
    </style>
    <title>查询结果</title>
</head>
<body>
<div class="ui one column page grid">

    <%
        if (cet != null) {//查询成功
    %>
    <div class="column">
        <div class="ui huge header icon center aligned">
            <i class="icon circular"><%=cet.getGrade()%>
            </i>
        </div>
    </div>

    <div class="column">
        <div class="ui two buttons fluid">
            <div class="ui button">听力</div>
            <div class="ui positive button"><%=cet.getListening()%>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="ui two buttons fluid">
            <div class="ui button">阅读</div>
            <div class="ui positive button"><%=cet.getReading()%>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="ui two buttons fluid">
            <div class="ui button">写作</div>
            <div class="ui positive button"><%=cet.getEssay()%>
            </div>
        </div>
    </div>
    <div class="column">
        <div class="ui two buttons fluid">
            <div class="ui button">总分</div>
            <%
                if (cet.pass()) {
            %>
            <div class="ui positive button"><%=cet.getSumScore()%>
            </div>
            <%
            } else {
            %>
            <div class="ui negative button"><%=cet.getSumScore()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <div class="column">
        <div class="ui horizontal divider icon">
            <i class="icon user"></i>
        </div>
    </div>

    <div class="column">
        <div class="ui center aligned basic segment" style="color: #808080">
            <span><%=cet.getName()%></span>,这是你在<span><%=cet.getDate()%></span>的<span>CET<%=cet.getGrade()%></span>成绩,全校排名<span><%=cet.rank()%></span>
        </div>
    </div>

    <div class="column center aligned">
        <a class="ui button mini" href="queryByIdNumber.jsp">不是你想要的？</a>
    </div>
    <%
    } else {
    %>
    <div class="column">
        <div class="ui segment red raised center aligned">
            囧，暂时还查不到你的成绩
        </div>
    </div>
    <%
        }
    %>
</div>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>
</body>
</html>