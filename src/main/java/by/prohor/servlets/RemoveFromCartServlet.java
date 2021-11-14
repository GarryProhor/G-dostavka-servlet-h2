package by.prohor.servlets;

import by.prohor.model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveFromCartServlet", value = "/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            if (id != null) {
                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (Cart c : cart_list) {
                        if (c.getId() == Integer.parseInt(id)) {
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                    response.sendRedirect("card.jsp");
                }
            } else {
                response.sendRedirect("card.jsp");
            }
        }
    }
}


