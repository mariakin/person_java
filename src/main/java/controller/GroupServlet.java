package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* Сервлет для работы с группами
*/
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GroupServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        // Установка атрибутов для передачи в JSP
        request.setAttribute("groupData", "Данные групп");
        request.setAttribute("pageTitle", "Управление группами");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/group.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Обработка POST-запросов (добавление/изменение групп)
        doGet(request, response);
    }
}