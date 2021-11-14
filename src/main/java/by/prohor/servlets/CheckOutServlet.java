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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "CheckOutServlet", value = "/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            //забираею все выбранные продукты
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            //вход пользователя
            User auth = (User) request.getSession().getAttribute("auth");

            if(cart_list != null && auth != null){
                for (Cart c:cart_list){
                    //готовлю ордер обьект
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(auth.getId());
                    order.setQunatity(c.getQuantity());
                    order.setDate(format.format(date));

                    OrderDAO orderDAO = new OrderDAO(DbConnection.getConnection());
                    boolean result = orderDAO.insertOrder(order);
                    if(!result)break;
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");

            }else {
                if(auth == null) response.sendRedirect("login.jsp");
                response.sendRedirect("card.jsp");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
