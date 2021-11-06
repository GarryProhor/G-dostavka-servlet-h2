<%@ page import="by.prohor.connection.DbConnection" %>
<%@ page import="java.sql.SQLException" %>
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
    <title>Shooping</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>

<%@include file="includes/navbar.jsp"%>

<% out.print(DbConnection.getConnection());%>

<%@include file="includes/footer.jsp"%>
</body>
</html>
