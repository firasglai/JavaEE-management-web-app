package com.example.crudscolarite.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.crudscolarite.Beans.Enseignant;

import com.example.crudscolarite.Beans.Groupe;
import com.example.crudscolarite.Dao.EnseignantDao;


@WebServlet(name = "EnseignantController", urlPatterns = {"/ListEns","/AjouterEns","/ModifierEns","/SupprimerEns"})
public class EnseignantController extends HttpServlet {
    private EnseignantDao ensDao ;

    public EnseignantController(){
        this.ensDao= new EnseignantDao();
    }

   RequestDispatcher dispatcher = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/AjouterEns":
        ajouterEns(request, response);
            break;
            case "/ModifierEns":
                try {
                    UpdateEns(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/SupprimerEns":
        deleteEns(request, response);
                break;
            default:
        listEns(request, response);
                break;

        }


    }

    private void listEns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enseignant> listEns= ensDao.listEns();

        request.setAttribute("list", listEns);

       dispatcher = request.getRequestDispatcher("Views/Enseignant/GestionEnseignant.jsp");
       dispatcher.forward(request, response);
    }



    private void ajouterEns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enseignant ens= new Enseignant();
        ens.setNomEns(request.getParameter("nomEns"));
        ens.setPrenomEns(request.getParameter("prenomEns"));
        ens.setEmailEns(request.getParameter("emailEns"));
        ens.setTelephoneEns(request.getParameter("telephoneEns"));
        ensDao.addEns(ens);
        response.sendRedirect("ListEns");
        // listUser(request, response);
    }

    private void deleteEns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String id = request.getParameter("id");
        ensDao.deleteEns(Integer.parseInt(id));
        // request.setAttribute("NOTIFICATION", "User Supprimé avec Succes!");
        response.sendRedirect("ListEns");

    }
    private void UpdateEns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String id = request.getParameter("id");
        Enseignant ens = new Enseignant();
        ens.setNomEns(request.getParameter("nomEns"));
        ens.setPrenomEns(request.getParameter("prenomEns"));
        ens.setEmailEns(request.getParameter("emailEns"));
        ens.setTelephoneEns(request.getParameter("telephoneEns"));
        ens.setIdEns(Integer.parseInt(id));
        ensDao.updateEns(ens);
        response.sendRedirect("ListEns");
    }



}
