<%@ page import="study.classroom.ManageClassroom" %>
<%@ page import="study.classroom.MyClassroomEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2014/3/31
  Time: 21:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>

  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <link href="../../../lib/css/semantic.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="../../../lib/css/main.css">

</head>
<body>

<div class="column">
  <table class="ui table">
    <thead>
    <tr>
      <th>几号楼</th>
      <th>星期几</th>
      <th>第几节课</th>
      <th>哪个教室</th>
    </tr>
    </thead>
    <%
      List<MyClassroomEntity> all = ManageClassroom.all();
      for (MyClassroomEntity one : all) {
    %>
    <tr>
      <td><%=one.getJiHaoLou()%>
      </td>
      <td><%=one.getXinQiJi()%>
      </td>
      <td><%=one.getDiJiJieKe()%>
      </td>
      <td><%=one.getClassroom()%>
      </td>
    </tr>
    <%
      }
    %>

  </table>
</div>

<script src="../../../lib/js/jquery.min.js"></script>
<script src="../../../lib/js/semantic.min.js"></script>
<script src="../../../lib/js/main.js"></script>
</body>
</html>
