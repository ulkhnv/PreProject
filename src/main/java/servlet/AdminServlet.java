package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", service.getAllUsers());
        req.getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        int age = Integer.parseInt(req.getParameter("age"));
        service.addUser(new User(name, password, role, age));
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
