package by.prohor.servlets;

import by.prohor.model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddToCardServlet", value = "/add-to-cart")
public class AddToCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out =response.getWriter()) {
            ArrayList<Cart> cartList = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");

            if(cart_List==null){
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");
            }else {
                cartList=cart_List;
                boolean exist = false;

                for(Cart c:cartList){
                 if (c.getId()==id){
                     exist = true;
                     out.println("<h3 style='color:crimson; text-align:center'>Item already exist in Cart.<a href='card.jsp'>Go to Cart Page</a></h3>");
                 }
                }
                if(!exist){
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }


        }
    }


}
