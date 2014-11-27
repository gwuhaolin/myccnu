<%@ page import="study.book.SearchBook" %>
<%@ page import="java.util.List" %>
<%--
  Created by Intellij IDEA.
  User: WuHaoLin
  Date: 2/27/14
  Time: 10:34 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../tool/error/index.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String want = request.getParameter("W");
    String pageIndex = request.getParameter("page");
    List<SearchBook.MyBook> books = SearchBook.get(want, pageIndex);
    if (books != null && books.size() > 0) {
        for (int i = 0; i < books.size(); i++) {
            SearchBook.MyBook book = books.get(i);
%>
<div class="column">

    <div class="ui stacked segment">
        <div class="ui image rounded fluid">
            <div class="ui label icon corner blue inverted">
                <i class="icon heart"></i>
            </div>
            <img src="<%=book.getImgUrl()%>">
        </div>
        <div class="ui horizontal icon divider"><i class="icon book"></i></div>
        <div class="ui ribbon label black huge"><%=book.getTitle()%>
        </div>
        <div class="ui relaxed divided mini list">

            <div class="item">
                <i class="user icon circular inverted blue"></i>

                <div class="content">
                    <a class="header"><%=book.getAuthor()%>
                    </a>

                    <div class="description">作者
                    </div>
                </div>
            </div>

            <div class="item">
                <i class="globe icon circular inverted black"></i>

                <div class="content">
                    <a class="header"><%=book.getPress()%>
                    </a>

                    <div class="description">出版
                    </div>
                </div>
            </div>

            <div class="item">
                <i class="star icon circular inverted <%=book.getBootStrapBtnType()%>"></i>

                <div class="content">
                    <a class="header"><%=book.getState()%>
                    </a>

                    <div class="description">状态
                    </div>
                </div>
            </div>
        </div>

        <a href="<%=book.getBookDetailInfoUrl()%>">
            <div class="ui label attached right bottom small green"><i class="icon tags"> 详细信息</i></div>
        </a>
    </div>
</div>
<%
    }
    if (books.size() < 10) {//如果学校的书太少,就荐购提示
%>
<a href="JianGou.jsp" class="ui button fluid black circular" style="margin-top: 10px">向学校荐购你想要的书</a>
<%
    }
} else {//没有找到,直接去荐购页
%>
<script>
    window.location = "JianGou.jsp";
</script>
<%
    }
%>



