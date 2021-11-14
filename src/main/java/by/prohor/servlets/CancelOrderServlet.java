package by.prohor.servlets;

import by.prohor.connection.DbConnection;
import by.prohor.dao.OrderDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CancelOrderServlet", value = "/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()){
            String id = request.getParameter("id");
            if(id!=null){
                OrderDAO orderDAO = new OrderDAO(DbConnection.getConnection());
                orderDAO.cancelOrder(Integer.parseInt(id));
            }
            response.sendRedirect("orders.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
