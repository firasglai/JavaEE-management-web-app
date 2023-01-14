package com.example.crudscolarite.Controller;
import com.example.crudscolarite.Beans.Enseignant;
import com.example.crudscolarite.Beans.Groupe;
import com.example.crudscolarite.Beans.Users;
import com.example.crudscolarite.Dao.EtudiantDao;
import com.example.crudscolarite.Beans.Etudiant;
import com.example.crudscolarite.Dao.GroupeDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EtudiantController", urlPatterns = {"/ListEtu","/ListEtuGrp","/AjouterEtu","/ModifierEtu","/SupprimerEtu"})
public class EtudiantController extends HttpServlet {

    private EtudiantDao etuDao;
    private GroupeDao grpdao;
    RequestDispatcher dispatcher = null;
    public EtudiantController() {this.etuDao=new EtudiantDao();
    this.grpdao= new GroupeDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
        case "/AjouterEtu":
            ajouterEtu(request, response);
            break;
        case "/ListEtuGrp":
           listEtuParGrp(request, response);
            break;
        case"/ModifierEtu":
            try {
                updateEtuvol2(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        case"/SupprimerEtu":
            deleteEtu(request, response);
            break;
        default:
            listEtu(request, response);
}
        }




    /*LIST ETUDIANT PAR ID*/
    private void listEtuParID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           String id = request.getParameter("idetu");
        List<Etudiant>listEtuParID= etuDao.listEtuParID(Integer.parseInt(id));
        request.setAttribute("listetu",listEtuParID);
        dispatcher =request.getRequestDispatcher("Views/Etudiant/EtudiantParGroupe.jsp");
        dispatcher.forward(request, response);
    }

    /*LIST ETUDIANT PAR GROUPE */
    private void listEtuParGrp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       String grp = request.getParameter("grp");
       List<Etudiant>listEtu= etuDao.listEtuParGrp(grp);
       request.setAttribute("listetugrp",listEtu);
       dispatcher =request.getRequestDispatcher("Views/Etudiant/EtudiantParGroupe.jsp");
        dispatcher.forward(request, response);
    }
    /*LISTE TOUS LES  ETUDIANT */
    private void listEtu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> listEtu= etuDao.listEtu();
        List<Groupe>listgrp=grpdao.listallgrp();
        request.setAttribute("listetu", listEtu);
        request.setAttribute("listgrp",listgrp);
        dispatcher = request.getRequestDispatcher("Views/Etudiant/GestionEtudiant.jsp");
        dispatcher.forward(request, response);

    }
/*AJOUTER ETUDIANT */
private void ajouterEtu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Etudiant etudiant = new Etudiant();
    etudiant.setNomEtu(request.getParameter("nom_etu"));
    etudiant.setPrenomEtu(request.getParameter("prenom_etu"));
    etudiant.setEmailEtu(request.getParameter("email_etu"));
    etudiant.setGroupNom(request.getParameter("nom_groupe"));
    etuDao.addEtu(etudiant);
    response.sendRedirect("ListEtu");
}
    /*SUPPRIMER ETUDIANT*/
    private void deleteEtu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        etuDao.deleteEns(Integer.parseInt(id));
        // request.setAttribute("NOTIFICATION", "User Supprimé avec Succes!");
        response.sendRedirect("ListEtu");
    }
    /*MODIFIER ETUDIANT*/
    private void updateEtu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        Etudiant etu = new Etudiant();
        etu.setNomEtu(request.getParameter("nomEtu"));
        etu.setPrenomEtu(request.getParameter("prenomEtu"));
        etu.setEmailEtu(request.getParameter("emailEtu"));
        etu.setGroupNom(request.getParameter("nom_groupe"));
        etu.setIdEtu(Integer.parseInt(id));
        etuDao.UpdateEtu(etu);
        response.sendRedirect("ListEtu");
    }

    /*UPDATE VOL2*/
    private void updateEtuvol2(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException  {
        String id = request.getParameter("id");
        Etudiant etu = new Etudiant();
        etu.setNomEtu(request.getParameter("nomEtu"));
        etu.setPrenomEtu(request.getParameter("prenomEtu"));
        etu.setEmailEtu(request.getParameter("emailEtu"));
        etu.setGroupNom(request.getParameter("grp"));
        etu.setIdEtu(Integer.parseInt(id));
        etuDao.updateEtuVol2(etu);
        response.sendRedirect("ListEtu");
    }

}
