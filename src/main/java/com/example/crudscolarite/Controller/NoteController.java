package com.example.crudscolarite.Controller;
import com.example.crudscolarite.Beans.Enseignant;
import com.example.crudscolarite.Beans.Matiere;
import com.example.crudscolarite.Dao.MatiereDao;
import com.example.crudscolarite.Beans.Etudiant;
import com.example.crudscolarite.Dao.EtudiantDao;
import com.example.crudscolarite.Beans.Note;
import com.example.crudscolarite.Dao.NotesDao;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "NoteController", urlPatterns = {"/ListNote","/ListeNoteParId","/AjouterNote","/SupprimerNote","/Export"})
public class NoteController extends HttpServlet {
    private EtudiantDao etudiantDao;
    private MatiereDao matiereDao;
    private NotesDao notesDao;

    public NoteController() {

        this.notesDao = new NotesDao();
        this.matiereDao = new MatiereDao();
        this.etudiantDao = new EtudiantDao();
    }

    RequestDispatcher dispatcher = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/ListeNoteParId":
                listNoteParId(request, response);
                break;
            case "/AjouterNote":
                ajoutNote(request, response);
                break;
            case "/SupprimerNote":
                SupressionNote(request, response);
                break;
            case"/Export":
                ExportToExcel(request, response);
                break;
            default:
                listNote(request, response);
        }
    }


    private void listNoteParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idetu");
        List<Note> noteList = notesDao.ListNoteParEtu(Integer.parseInt(id));
        request.setAttribute("listenotes", noteList);
        dispatcher = request.getRequestDispatcher("Views/Notes/ReleveNote.jsp");
        dispatcher.forward(request, response);
    }

    private void listNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Note> noteList = notesDao.listAllNote();
        List<Etudiant> etudiantList = etudiantDao.listEtu();
        List<Matiere> matiereList = matiereDao.listMat();
        request.setAttribute("listnote", noteList);
        request.setAttribute("lisetu", etudiantList);
        request.setAttribute("listmat", matiereList);
        dispatcher = request.getRequestDispatcher("Views/Notes/ListNotes.jsp");
        dispatcher.forward(request, response);
    }

    private void ajoutNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = new Note();
        note.setNote_pratique(Float.parseFloat(request.getParameter("note_pratique")));
        note.setNote_examen(Float.parseFloat(request.getParameter("note_examen")));
        note.setNote_controle(Float.parseFloat(request.getParameter("note_controle")));
        note.setId_etu(Integer.parseInt(request.getParameter("id_etu")));
        note.setCode_mat(Integer.parseInt(request.getParameter("code_mat")));
        notesDao.ajouterNote(note);
        response.sendRedirect("ListNote");

    }

    private void SupressionNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        notesDao.deleteNote(Integer.parseInt(id));
        // request.setAttribute("NOTIFICATION", "User Supprimé avec Succes!");
        response.sendRedirect("ListNote");
    }

    private void UpdateNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    private void ExportToExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Note>list= (List<Note>) request.getAttribute("listenotes");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Notes");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Code Etudiant");
        headerRow.createCell(1).setCellValue("Contrôle");
        headerRow.createCell(2).setCellValue("Examen");
        headerRow.createCell(3).setCellValue("TP");
        headerRow.createCell(4).setCellValue("Id Matière");
        int rowNum = 1;
        for (Note note : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(note.getId_etu());
            row.createCell(1).setCellValue(note.getNote_controle());
            row.createCell(2).setCellValue(note.getNote_examen());
            row.createCell(3).setCellValue(note.getNote_pratique());
            row.createCell(4).setCellValue(note.getCode_mat());
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Notes", "attachment; filename=Notes.xlsx");
        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }

    }
}
