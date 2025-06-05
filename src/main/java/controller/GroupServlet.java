package controller;

import jakarta.servlet.RequestDispatcher;
import dataaccess.ConnectionProperty;
import dataaccess.GroupDbDAO;
import domain.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public GroupServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String userPath;
        List<Group> groups;
        GroupDbDAO dao = new GroupDbDAO();
        try {
            groups = dao.findAll();
            request.setAttribute("groups", groups);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        userPath = request.getServletPath();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/group.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	GroupDbDAO dao = new GroupDbDAO();
    	  
    	    // Получаем параметры из формы
    	    String name = request.getParameter("name");
    	    String faculty = request.getParameter("faculty");
    		int course = Integer.parseInt(request.getParameter("course"));
    	    String educationType = request.getParameter("educationType");
    	    
    	    // Создаем новый объект Group
    	    Group newGroup = new Group();
    	    newGroup.setId(5l);
    	    newGroup.setName(name);
    	    newGroup.setFaculty(faculty);
    	    newGroup.setCourse(course);
    	    newGroup.setEducationType(educationType);
    	    
    	    try {
    	        // Добавляем группу в базу данных
    	        Long index = dao.insert(newGroup);
    	        System.out.println("Группа успешно добавлен. ID: " + index);
    	        
    	        // Перенаправляем на страницу с обновленным списком
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/group.jsp");
    		    dispatcher.include(request, response);
    	 
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        // В случае ошибки сохраняем сообщение и возвращаем на форму
    	        request.setAttribute("errorMessage", "Ошибка при добавлении группы: " + e.getMessage());
    	        request.getRequestDispatcher("/views/group.jsp").forward(request, response);
    	    }
    	}
    
}