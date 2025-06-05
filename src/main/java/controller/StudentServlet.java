package controller;

import domain.Student;
import domain.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
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

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        StudentDbDAO daoStudent = new StudentDbDAO();
        GroupDbDAO daoGroup = new GroupDbDAO();
        
        try {
            // Получаем список всех групп для выпадающего списка
            List<Group> groups = daoGroup.findAll();
            request.setAttribute("groups", groups);
            
            // Получаем параметры из формы
            String lastName = request.getParameter("last_name");
            String firstName = request.getParameter("first_name");
            String middleName = request.getParameter("middle_name");
            String birthDate = request.getParameter("birth_date");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String groupParam = request.getParameter("groups");
            String id = request.getParameter("id");
            
            String digitsOnly = groupParam.replaceAll("\\D+", "");
            Long groupId;
            if (!digitsOnly.isEmpty()) {
                groupId = Long.parseLong(digitsOnly);
            } else {
                throw new ServletException("Не удалось извлечь ID студента из параметра: " + groupParam);
            }
            
            String digitsOnlyId = id.replaceAll("\\D+", "");
            Long idId;
            if (!digitsOnlyId.isEmpty()) {
            	idId = Long.parseLong(digitsOnlyId);
            } else {
                throw new ServletException("Не удалось извлечь ID студента из параметра: " + id);
            }
            
            // Находим полный объект Group по ID
            Group group = findById(groupId, groups);
            
            // Создаем нового студента
            Student newStudent = new Student();
            newStudent.setId(idId);
            newStudent.setLastName(lastName);
            newStudent.setFirstName(firstName);
            newStudent.setMiddleName(middleName);
            newStudent.setBirthDate(birthDate);
            newStudent.setPhone(phone);
            newStudent.setEmail(email);
            newStudent.setGroup(group);

            
            // Добавляем персонажа в базу данных
            Long index = daoStudent.insert(newStudent);
            System.out.println("Персонаж успешно добавлен. ID: " + index);
            // Перенаправляем на страницу с обновленным списком
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/students.jsp");
		    dispatcher.include(request, response);
		 // После успешного добавления делаем редирект
	        response.sendRedirect(request.getContextPath() + "/student");
	        
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка при добавлении: " + e.getMessage());
            request.getRequestDispatcher("/views/students.jsp").forward(request, response);
        }
        

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