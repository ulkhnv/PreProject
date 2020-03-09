package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/menu"})
public class MenuServlet extends HttpServlet {
    private UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if (service.validateUser(name, password)) {
            String role = service.getUserRole(name);
            if (role.equals("admin")) {
                session.setAttribute("name", name);
                session.setAttribute("role", role);
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else if (role.equals("user")) {
                session.setAttribute("name", name);
                session.setAttribute("role", role);
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        }


    }
}
