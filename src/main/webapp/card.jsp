<%@ page import="by.prohor.model.User" %>
<%@ page import="by.prohor.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="by.prohor.dao.ProductDAO" %>
<%@ page import="by.prohor.connection.DbConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth =(User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        ProductDAO pDao = new ProductDAO(DbConnection.getConnection());
        cartProduct = pDao.getCartProducts(cart_list);
        request.setAttribute("cart_list", cart_list);
    }
%>
<html>
<head>
    <title>Card page</title>
    <%@include file="includes/head.jsp"%>
    <style type="text/css">
        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="d-flex py-3"><h3>Total Price: $ 452</h3><a class="mx-3 btn btn-primary" href="#">Check OutS</a></div>
    <table class="table table-loght">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Price</th>
        <th scope="col">Buy Now</th>
        <th scope="col">Cancel</th>
    </tr>
    </thead>
        <tbody>
        <%
            if (cart_list != null) {
                for (Cart c : cartProduct) {
        %>
        <tr>
            <td><%=c.getName()%></td>
            <td><%=c.getCategory()%></td>
            <td><%=c.getPrice()%></td>
            <td>
                <form action="order-now" method="post" class="form-inline">
                    <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                    <div class="form-group d-flex justify-content-between">
                        <a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
                        <input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly>
                        <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                </form>
            </td>
            <td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
        </tr>

        <%
                }}%>
        </tbody>
    </table>

</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
