package by.prohor.servlets;

import by.prohor.connection.DbConnection;
import by.prohor.dao.OrderDAO;
import by.prohor.model.Cart;
import by.prohor.model.Order;
import by.prohor.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "OrderNowServlet", value = "/order-now")
public class OrderNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");
            if(auth != null){
                String productId  = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if(productQuantity<=0){
                    productQuantity=1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUid(auth.getId());
                orderModel.setQunatity(productQuantity);
                orderModel.setDate(format.format(date));

                OrderDAO orderDAO = new OrderDAO(DbConnection.getConnection());
                boolean result = orderDAO.insertOrder(orderModel);

                if(result){
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    response.sendRedirect("orders.jsp");
                }else {
                    out.println("orders failed");
                }

            }else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
