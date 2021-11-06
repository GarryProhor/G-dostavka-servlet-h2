<%@ page import="by.prohor.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth =(User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
%>
<html>
<head>
    <title>Order page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
fgdsfd

<%@include file="includes/footer.jsp"%>
</body>
</html>
