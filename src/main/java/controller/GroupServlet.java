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
        doGet(request, response);
    }
}