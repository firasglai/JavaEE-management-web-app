package com.example.crudscolarite.Controller;
import com.example.crudscolarite.Beans.Note;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CalculMoyen", value = "/CalculMoyen")
public class CalculMoyen extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Note>listdesnotes= (List<Note>) request.getAttribute("listenotes");
        double sum = 0;

    }
}
