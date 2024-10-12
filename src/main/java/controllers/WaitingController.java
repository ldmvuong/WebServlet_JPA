package controllers;

import entity.Role;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            req.setAttribute("username", u.getUsername());

            Role role = u.getRole();
            if (role != null) {
                if (role.getRoleid() == 1) {
                    resp.sendRedirect(req.getContextPath() + "/admin/home");
                } else if (role.getRoleid() == 2) {
                    resp.sendRedirect(req.getContextPath() + "/manager/home");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/user/home");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
