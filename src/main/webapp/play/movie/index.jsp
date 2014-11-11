<%@ page import="play.movie.ManageMovie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <script src="../../lib/js/jquery.min.js"></script>
  <link href="../../lib/css/semantic.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">
  <script src="../../lib/css/javascript/semantic.min.js"></script>

  <script src="../../lib/js/main.js"></script>
  <title>校园电影</title>
</head>
<body>

<div class="ui three column stackable page grid">
  <div class="ui divider horizontal icon inverted">
    <i class="icon users"></i>
  </div>
  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="list.jsp?target=<%=ManageMovie.Target_HDQNJC%>">
        <i class="icon facetime video circular inverted green"></i>
      </a>

      <div class="ui label circular green">华大青年剧场</div>
    </div>
  </div>
  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="list.jsp?target=<%=ManageMovie.Target_LTDYC%>">
        <i class="icon video circular inverted red"></i>
      </a>

      <div class="ui label circular red">露天电影场</div>
    </div>
  </div>
  <div class="column">
    <div class="ui header icon center aligned huge">
      <a href="list.jsp?target=<%=ManageMovie.Target_iWANT%>">
        <i class="icon share circular inverted purple"></i>
      </a>

      <div class="ui label circular purple">大家的分享</div>
    </div>
  </div>

</div>

</body>
</html>
