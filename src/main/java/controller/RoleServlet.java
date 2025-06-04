package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки ролей пользователей.
 * Доступен по URL: /HelloRoleServlet
 */
@WebServlet("/HelloRoleServlet")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор по умолчанию
     */
    public RoleServlet() {
        super();
    }

    /**
     * Обрабатывает GET-запросы.
     * Отправляет HTML-страницу с приветствием.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8"); // Указываем кодировку UTF-8
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head><title>RoleServlet</title></head>");
            writer.println("<body>");
            writer.println("<h2>Привет, RoleServlet!</h2>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }

    /**
     * Обрабатывает POST-запросы.
     * Перенаправляет запрос в doGet().
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}