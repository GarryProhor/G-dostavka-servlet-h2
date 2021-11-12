<%@ page import="by.prohor.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User auth =(User) request.getSession().getAttribute("auth");
  if(auth != null){

    response.sendRedirect("index.jsp");
  }
%>
<html>
<head>
  <title>Registration page</title>
  <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
  <div class="card w-50 mx-auto my-5">
    <div class="card-header text-center">Registration</div>
    <div class="card-body">
      <form action="user-registration" method="post">
        <div class="form-group">
          <label>User Name</label>
          <input type="text" class="form-control" name="User-name" placeholder="Enter Your Name" required>
        </div>
        <div class="form-group">
          <label>Email address</label>
          <input type="email" class="form-control" name="Login-email" placeholder="Enter Your Email" required>
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" class="form-control" name="Login-password" placeholder="*********" required>
        </div>

        <div class="text-center">
          <button type="submit" class="btn btn-primary">Registration</button>
        </div>
      </form>
    </div>

  </div>

</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>