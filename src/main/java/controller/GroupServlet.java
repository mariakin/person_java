package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для обработки групп пользователей.
 * Доступен по URL: /HelloGroupServlet
 */
@WebServlet("/HelloGroupServlet")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор по умолчанию
     */
    public GroupServlet() {
        super();
    }

    /**
     * Обрабатывает GET-запросы.
     * Отправляет HTML-страницу с приветствием.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head><title>GroupServlet</title></head>");
            writer.println("<body>");
            writer.println("<h2>Привет, GroupServlet!</h2>"); // Исправлено с RoleServlet на GroupServlet
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