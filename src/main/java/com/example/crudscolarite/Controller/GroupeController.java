package com.example.crudscolarite.Controller;
import com.example.crudscolarite.Beans.Specialite;
import com.example.crudscolarite.Dao.SpecialiteDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.example.crudscolarite.Beans.Groupe;
import com.example.crudscolarite.Beans.Users;
import com.example.crudscolarite.Dao.GroupeDao;
@WebServlet(name = "GroupeController", value = {"/groupe"})
public class GroupeController extends HttpServlet {
    private SpecialiteDao specdao;
    private GroupeDao groupeDao;
    RequestDispatcher dispatcher = null;

    public GroupeController(){
        this.groupeDao=new GroupeDao();
        this.specdao=new SpecialiteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    listGrp(request, response);

    }

    private void listGrp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idgrp");
        List<Groupe>listgrp =groupeDao.listgrp(id);
        List<Specialite>listspec=specdao.listSpec();
        request.setAttribute("listgrp",listgrp);
        dispatcher = request.getRequestDispatcher("Views/Cards/ListeGrp.jsp");
        dispatcher.forward(request, response);
    }
    private void listallGrp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Groupe>listgrp=groupeDao.listallgrp();
    }


}
