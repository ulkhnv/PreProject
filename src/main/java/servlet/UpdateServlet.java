package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/update"})
public class UpdateServlet extends HttpServlet {
    UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", service.getAllUsers());
        req.setAttribute("id", req.getParameter("id"));
        req.getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        int age = Integer.parseInt(req.getParameter("age"));
        if (!name.isEmpty() && !password.isEmpty() && !role.isEmpty() && age > 0) {
            service.updateUser(new User(id, name, password, role, age));
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
    }
}
