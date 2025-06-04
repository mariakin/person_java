package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* Сервлет для работы со студентами
*/
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        // Установка атрибутов для передачи в JSP
        request.setAttribute("studentData", "Данные студентов");
        request.setAttribute("pageTitle", "Управление студентами");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/student.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Обработка POST-запросов (добавление/изменение студентов)
        doGet(request, response);
    }
}