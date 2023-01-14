package com.example.crudscolarite.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import com.example.crudscolarite.Beans.Users;
import com.example.crudscolarite.Dao.AuthentificationDao;
@WebServlet(name = "Register", value = "/Register")

public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");
        String mobile = request.getParameter("contact");
        Users user = new Users(username, password, email, mobile);
        AuthentificationDao rDao = new AuthentificationDao();
        String result = rDao.register(user);
      //  response.getWriter().println(result);
      response.sendRedirect("RegSucc.html");

    }


}
