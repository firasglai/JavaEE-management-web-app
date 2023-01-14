package com.example.crudscolarite.Controller;
import com.example.crudscolarite.Beans.Etudiant;
import com.example.crudscolarite.Beans.Specialite;
import com.example.crudscolarite.Dao.SpecialiteDao;
import com.example.crudscolarite.Beans.Matiere;
import com.example.crudscolarite.Dao.MatiereDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatiereController", urlPatterns = {"/ListMat","/AjouterMat","/ModifierMat","/SupprimerMat","/ListMatParSpec"})
public class MatiereController extends HttpServlet {
    private  SpecialiteDao specDao;
    private MatiereDao matDao;
    public MatiereController()
    {
        this.matDao=new MatiereDao();
    this.specDao=new SpecialiteDao();
    }
    RequestDispatcher dispatcher = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*APPEL AU METHODE GET*/
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String action = request.getServletPath();
     switch (action){
         case "/AjouterMat":
             ajouterMat(request, response);
             break;
         case"/ModifierMat":
             try {
                 updateMat(request, response);
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
             break;
         case"/SupprimerMat":
             deleteMat(request, response);
             break;
         case"/ListMatParSpec":
             try {
                 listMatFilter(request, response);
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
             break;

         default:
             listMat(request, response);
             break;
     }
    }

    private void listMat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Specialite>listSpec=specDao.listSpec();
        List<Matiere>listMat = matDao.listMat();
        request.setAttribute("listspec",listSpec);
        request.setAttribute("listmat", listMat);
        dispatcher =request.getRequestDispatcher("Views/Matiere/GestionMatiere.jsp");
        dispatcher.forward(request,response);
    }

    private void ajouterMat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Matiere matiere = new Matiere();
        request.setCharacterEncoding("UTF-8");
        matiere.setNom_mat(request.getParameter("nom_mat"));
        matiere.setCoef(Integer.parseInt(request.getParameter("coef_mat")));
        matiere.setNom_spec(request.getParameter("spec"));
        matiere.setId_niv(Integer.parseInt(request.getParameter("niv")));
        matDao.addMat(matiere);

        //response.sendRedirect("ListMat");
         listMat(request, response);
    }
    private void deleteMat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        matDao.deleteMat(Integer.parseInt(id));
        // request.setAttribute("NOTIFICATION", "User Supprimé avec Succes!");
        response.sendRedirect("ListMat");
    }
    private void updateMat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Matiere mat = new Matiere();
        mat.setNom_mat(request.getParameter("nom_mat"));
        mat.setCoef(Integer.parseInt(request.getParameter("coef_mat")));
        mat.setNom_spec(request.getParameter("spec"));
        mat.setId_niv(Integer.parseInt(request.getParameter("niv")));
        mat.setCode_mat(Integer.parseInt(id));
        matDao.UpdateMatvol2(mat);
        response.sendRedirect("ListMat");

    }
    private void listMatFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        String spec = request.getParameter("specialite");
        List<Matiere>listMat = matDao.listMatParSec(spec);
        request.setAttribute("listmat",listMat);
        dispatcher =request.getRequestDispatcher("Views/Matiere/GestionMatiere.jsp");
        dispatcher.forward(request,response);

    }
}
