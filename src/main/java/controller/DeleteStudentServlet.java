package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import dataaccess.ConnectionProperty;
import dataaccess.StudentDbDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deletestudent")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public DeleteStudentServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDbDAO dao = new StudentDbDAO();
        String strId = request.getParameter("id");
        Long deleteid = null;
        
        if(strId != null) {
            deleteid = Long.parseLong(strId);
        }
        
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Ошибка при удалении студента: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/student");
    }
}