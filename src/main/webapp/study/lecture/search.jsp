<%@ page import="tool.Tool" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--通知列表--%>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/21/14
  Time: 3:01 PM
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
    <title>搜索结果</title>
</head>
<body>
<div class="ui stackable three column page grid">
    <%
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String want = request.getParameter("want");
        int target = Integer.parseInt(request.getParameter("target"));
    %>
    <%--默认拿出前changeCount通知--%>
    <jsp:include page="GetAJAXServlet.jsp">
        <jsp:param name="begin" value="0"/>
        <jsp:param name="want" value="<%=want%>"/>
        <jsp:param name="target" value="<%=target%>"/>
    </jsp:include>
    <%=Tool.makeAjaxLoadMoreBtnHtml("showHighLight('" + want + "')")%>
</div>

<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>

<jsp:include page="searchBox.jsp">
    <jsp:param name="target" value="<%=target%>"/>
</jsp:include>

<script>
    showHighLight('<%=want%>');
    closeWeiXinBtn();
    <%
    Map<String,Object> params=new HashMap<>(1);
    params.put("want",want);
    params.put("target",target);
    %>
    <%=Tool.makeAJAXLoadMoreJS("GetAJAXServlet.jsp",params)%>
</script>

</body>
</html>
