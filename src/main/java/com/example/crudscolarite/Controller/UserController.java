package com.example.crudscolarite.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.crudscolarite.Dao.UserDao;
import com.example.crudscolarite.Beans.Users;

@WebServlet(name = "UserController", urlPatterns = {"/ListUser","/Edit","/ModifierUser","/SupprimerUser","/AjouterUser"})
public class UserController extends HttpServlet {
    private UserDao userDao;
    RequestDispatcher dispatcher = null;
public UserController(){
    this.userDao= new UserDao();
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch(action) {
            case "/AjouterUser":
                addUser(request,response);
                break;
            case "/Edit":
                getSingleUser(request, response);
                break;
            case "/ModifierUser":
                try {
                    UpdateUser(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/SupprimerUser":
             deleteOneUser(request,response);
                break;
            default:
                listUser(request,response);
                break;

        }
    }

    private void deleteOneUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
       userDao.deleteUser(Integer.parseInt(id));
           // request.setAttribute("NOTIFICATION", "User Supprimé avec Succes!");
        response.sendRedirect("ListUser");

    }

    /*ADD USER */
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users u = new Users();
        u.setUname(request.getParameter("username"));
        u.setPassword(request.getParameter("password"));
        u.setEmail(request.getParameter("email"));
        u.setPhone(request.getParameter("phone"));
        userDao.addUser(u);
       response.sendRedirect("ListUser");
      // listUser(request, response);
    }



    private void getSingleUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");

        Users user = userDao.getoneUser(Integer.parseInt(id));

        request.setAttribute("user", user);

        dispatcher = request.getRequestDispatcher("Views/Admin/UserForm.jsp");

        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> theList = userDao.getUser();

        request.setAttribute("list", theList);

        dispatcher = request.getRequestDispatcher("Views/Admin/GestionAdmin.jsp");

        dispatcher.forward(request, response);

    }


    /*Upate User vol 2  servlet*/
    private void UpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        Users user = new Users();
        user.setUname(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setId(Integer.parseInt(id));
        userDao.updateUser(user);
        response.sendRedirect("ListUser");
    }


    }










