<%@ page import="java.util.List" %>
<%@ page import="play.shudong.MyShuDongEntity" %>
<%@ page import="play.shudong.ManageShuDong" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  try {
    int begin = Integer.parseInt(request.getParameter("begin"));
    List<MyShuDongEntity> shudongs = ManageShuDong.get_page(begin);//按照发布时间获得树洞
    for (MyShuDongEntity one : shudongs) {
%>
<div class="thumbnail">
  <%--修改树洞表单--%>
  <form action="ManageServlet.jsp" method="post">
    <div class="thumbnail alert-warning">
      <div class="input-group">
        <span class="input-group-addon">内容</span>
        <input class="form-control" type="text" name="content" value="<%=one.getContent()%>">
      </div>
      <div class="input-group">
        <span class="input-group-addon">发布时间</span>
        <input class="form-control" type="text" name="date" value="<%=one.getDate()%>">
      </div>
      <div class="input-group">
        <span class="input-group-addon">浏览次数</span>
        <input class="form-control" type="text" name="seeCount" value="<%=one.getSeeCount()%>">
      </div>
      <%--学号--%>
      <a class="btn" href="/java/studentsInfo/index.jsp?XH=<%=one.getXh()%>"><%=one.getXh()%>
      </a>

    </div>
    <input type="hidden" name="id" value="<%=one.getId()%>">
    <input type="hidden" name="cmd" value="<%=ManageShuDong.CMD_Change%>">
    <input type="submit" class="form-control btn-info" value="修改">
  </form>

  <%--删除树洞表单--%>
  <form action="ManageServlet.jsp" method="post">
    <input type="hidden" name="id" value="<%=one.getId()%>">
    <input type="hidden" name="cmd" value="<%=ManageShuDong.CMD_Delete%>">
    <input type="submit" class="form-control btn-danger" value="删除">
  </form>
</div>
<%
    }
  } catch (Exception e) {
    return;
  }
%>
