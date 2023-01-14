package com.example.crudscolarite.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import com.example.crudscolarite.Beans.Users;
import com.example.crudscolarite.dbConnection.GetConnection;
import com.example.crudscolarite.Dao.AuthentificationDao;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    AuthentificationDao rDao = new AuthentificationDao();
RequestDispatcher dispatcher = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
Users user = new Users();
user.setUname(request.getParameter("username"));
user.setPassword(request.getParameter("password"));

String result = rDao.loginCheck(user);
if (result.equals("true")){
    session.setAttribute("username",user.getUname());
    dispatcher = request.getRequestDispatcher("Dashboard.jsp");
}else{
    request.setAttribute("status","failed");
    dispatcher = request.getRequestDispatcher("index.jsp");
}
dispatcher.forward(request,response);
    }
}
