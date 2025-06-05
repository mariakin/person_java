package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dataaccess.ConnectionProperty;
import dataaccess.GroupDbDAO;
import domain.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editgroup")
public class EditGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditGroupServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        GroupDbDAO dao = new GroupDbDAO();
        Group editGroup = null;

        String strId = request.getParameter("id");
        Long id = null;
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        try {
            editGroup = dao.findById(id);
            request.setAttribute("groupEdit", editGroup);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при получении данных группы");
        }
        
        request.getRequestDispatcher("/views/editgroup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupDbDAO dao = new GroupDbDAO();
        
        String strId = request.getParameter("id");
        Long id = Long.parseLong(strId);
        String name = request.getParameter("name");
        String faculty = request.getParameter("faculty");
        int course = Integer.parseInt(request.getParameter("course"));
        String educationType = request.getParameter("educationType");
        
        Group editGroup = new Group();
        editGroup.setId(id);
        editGroup.setName(name);
        editGroup.setFaculty(faculty);
        editGroup.setCourse(course);
        editGroup.setEducationType(educationType);
        
        try {
            dao.update(editGroup);
            request.getSession().setAttribute("message", "Группа успешно обновлена");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ошибка при обновлении группы");
        }
        
        response.sendRedirect(request.getContextPath() + "/group");
    }
}