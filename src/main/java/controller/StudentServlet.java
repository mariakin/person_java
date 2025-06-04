package controller;

import domain.Student;
import domain.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dataaccess.StudentDbDAO;
import dataaccess.GroupDbDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userPath;
        List<Student> students;
        List<Group> groups;
        
        GroupDbDAO daoGroup = new GroupDbDAO();
        StudentDbDAO daoStudent = new StudentDbDAO();
        
        try {
            students = daoStudent.findAll();
            groups = daoGroup.findAll();
            
            // Устанавливаем полные объекты Group для каждого студента
            for (Student student : students) {
                Group group = findById(student.getGroup().getId(), groups);
                student.setGroup(group);
            }
            
            request.setAttribute("students", students);
            request.setAttribute("groups", groups);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        userPath = request.getServletPath();
        request.getRequestDispatcher("/views/student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private Group findById(Long id, List<Group> groups) {
        if (groups != null) {
            for (Group group : groups) {
                if (group.getId().equals(id)) {
                    return group;
                }
            }
        }
        return null;
    }
}