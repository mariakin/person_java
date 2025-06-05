package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dataaccess.ConnectionProperty;
import dataaccess.GroupDbDAO;
import dataaccess.StudentDbDAO;
import domain.Group;
import domain.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editstudent")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditStudentServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        StudentDbDAO studentDao = new StudentDbDAO();
        GroupDbDAO groupDao = new GroupDbDAO();
        Student editStudent = null;

        try {
            List<Group> groups = groupDao.findAll();
            request.setAttribute("groups", groups);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String strId = request.getParameter("id");
        Long id = null;
        if(strId != null) {
            id = Long.parseLong(strId);
        }
        
        try {
            editStudent = studentDao.findById(id);
            request.setAttribute("studentEdit", editStudent);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при получении данных студента");
        }
        
        request.getRequestDispatcher("/views/editstudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        StudentDbDAO studentDao = new StudentDbDAO();
        GroupDbDAO groupDao = new GroupDbDAO();
        
        String strId = request.getParameter("id");
        Long id = Long.parseLong(strId);
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Long groupId = Long.parseLong(request.getParameter("groupId"));
        
        try {
            Group group = groupDao.findById(groupId);
            Student editStudent = new Student();
            editStudent.setId(id);
            editStudent.setLastName(lastName);
            editStudent.setFirstName(firstName);
            editStudent.setMiddleName(middleName);
            editStudent.setBirthDate(birthDate);
            editStudent.setPhone(phone);
            editStudent.setEmail(email);
            editStudent.setGroup(group);
            
            studentDao.update(editStudent);
            request.getSession().setAttribute("message", "Студент успешно обновлен");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Ошибка при обновлении студента");
        }
        
        response.sendRedirect(request.getContextPath() + "/student");
    }
}