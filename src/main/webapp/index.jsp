<%@ page import="by.prohor.connection.DbConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shooping</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>

<%@include file="includes/navbar.jsp"%>

<% System.out.print((DbConnection.getConnection()));%>

<%@include file="includes/footer.jsp"%>
</body>
</html>
