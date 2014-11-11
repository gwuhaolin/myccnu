<%@ page import="study.examin.ManageExamin" %>
<%@ page import="study.examin.OneExamin" %>
<%@ page import="tool.Tool" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/29
  Time: 16:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <link href="../../lib/css/semantic.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
  <title>考试时间查询</title>
</head>
<body>
<%
  List<OneExamin> all;
  String dep = request.getParameter("dep");
  String grade = request.getParameter("grade");
  if (dep != null) {
    all = ManageExamin.f(dep, grade);
  } else {
    all = ManageExamin.f(Tool.getXHMMfromCookie(request)[0]);
  }
%>
<div class="ui three column center aligned stackable page grid">

  <div class="column">
    <div class="ui message"><%=all.get(0).getName()%>级 <%=all.get(0).getTeacher()%> 的考试安排</div>
  </div>

  <%
    for (int i = 1; i < all.size(); i++) {
      OneExamin one = all.get(i);
  %>
  <div class="column">


    <div class="ui stacked segment">

      <div class="ui header center aligned"><%=one.getName()%>
      </div>

      <div class="ui selection list">
        <div class="item">
          <i class="icon user"></i>

          <div class="content">
            <div class="header"><%=one.getTeacher()%>
            </div>
          </div>
        </div>

        <div class="item">
          <i class="icon time"></i>

          <div class="content">
            <div class="header"><%=one.getTime()%>
            </div>
          </div>
        </div>

        <div class="item">
          <i class="icon map"></i>

          <div class="content">
            <div class="header"><%=one.locations()%>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>
  <%
    }
  %>

</div>

<%--隐藏于左边的功能框--%>
<div>
  <%--隐藏于左边的搜索框--%>
  <div class="ui styled sidebar floating">
    <div class="ui header center aligned icon">
      <i class="icon circular calendar blue inverted"></i>
    </div>
    <form class="ui form" method="post" action="index.jsp">

      <div class="field">
        <select name="dep" class="ui button fluid small">
          <option selected>哪个院?</option>
          <%
            for (String one : ManageExamin.Deps.keySet()) {
          %>
          <option value="<%=one%>"><%=one%>
          </option>
          <%
            }
          %>
        </select>
      </div>

      <div class="field">
        <select name="grade" class="ui button fluid small">
          <option value="-1" selected>哪个年级?</option>
          <option value="2014">2014</option>
          <option value="2013">2013</option>
          <option value="2012">2012</option>
          <option value="2011">2011</option>
        </select>
      </div>

      <div class="field">
        <input type="submit" class="ui black fluid button" value="GO"/>
      </div>

    </form>
  </div>

  <%--打开隐藏于左边的搜索框的按钮--%>
  <i class="ui add sign icon link big" onclick="showSearchBox(this)"
     style="position: fixed;bottom: 5px;right: 5px"></i>

  <script src="../../lib/js/jquery.min.js"></script>
  <script src="../../lib/js/semantic.min.js"></script>
  <script src="../../lib/js/main.js"></script>

  <script>
    closeWeiXinBtn();
    function showSearchBox(btn) {
      $('.sidebar').first().sidebar({
        overlay: true
      }).sidebar('toggle');
      $(btn).toggleClass('red');
      $(btn).toggleClass('add');
      $(btn).toggleClass('remove ');
    }
  </script>
</div>

</body>
</html>

