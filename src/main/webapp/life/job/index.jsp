<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="../../lib/css/semantic.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../lib/css/main.css">

    <title>家教兼职</title>
</head>
<body>

<div class="ui two column stackable page grid">

    <div class="ui divider horizontal icon inverted">
        <i class="icon leaf"></i>
    </div>

    <div class="column">
        <div class="ui header icon center aligned huge">
            <a href="PartTimeJobs.jsp">
                <i class="icon briefcase circular inverted red"></i>
            </a>

            <div class="ui label circular red">兼职</div>
        </div>
    </div>
    <div class="column">
        <div class="ui header icon center aligned huge">
            <a href="PrivateTeacher.jsp">
                <i class="icon book circular inverted green"></i>
            </a>

            <div class="ui label circular green">家教</div>
        </div>
    </div>


</div>

<jsp:include page="searchBox.jsp"/>

<script src="../../lib/js/jquery.min.js"></script>
<script src="../../lib/js/semantic.min.js"></script>
<script src="../../lib/js/main.js"></script>

</body>
</html>
