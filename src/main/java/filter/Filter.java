package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String menuURI = req.getContextPath() + "/menu";
        String adminURI = req.getContextPath() + "/admin";
        String userURI = req.getContextPath() + "/user";

        boolean loggedIn = session != null && session.getAttribute("name") != null && session.getAttribute("role") != null;

        if (!req.getRequestURI().equals(menuURI) && !loggedIn) {
            resp.sendRedirect(menuURI);
            return;
        }

        if (req.getRequestURI().startsWith(adminURI)) {
            if (!session.getAttribute("role").equals("admin")) {
                resp.sendRedirect(userURI);
                return;
            }
        }
        filterChain.doFilter(req,resp);

    }

    @Override
    public void destroy() {
    }
}

