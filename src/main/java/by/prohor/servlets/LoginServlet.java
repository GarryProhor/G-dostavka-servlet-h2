package by.prohor.servlets;

import by.prohor.connection.DbConnection;
import by.prohor.dao.UserDao;
import by.prohor.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/user-login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       try(PrintWriter out = response.getWriter()){
            String email = request.getParameter("Login-email");
            String password = request.getParameter("Login-password");

           try {
               UserDao userDao = new UserDao(DbConnection.getConnection());
               User user = userDao.userLogin(email, password);

               if(user!=null){
                   out.print("user login");
                   request.getSession().setAttribute("auth", user);
                   response.sendRedirect("index.jsp");
               }else {
                   out.print("user login failed");
               }
           } catch (ClassNotFoundException | SQLException e) {
               e.printStackTrace();
           }

       }
    }
}
