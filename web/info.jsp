<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/7/25
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <%
            String result = (String)request.getAttribute("info");
        %>
        <font style="color: red;font-size: 45px" >
            <%=result%>
        </font>
    </center>
</body>
</html>
