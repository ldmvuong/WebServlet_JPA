package controllers;

import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.IUserService;
import services.impl.UserServiceImpl;
import ultils.Constant;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.LOGIN);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String alertMsg = "";

        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.LOGIN).forward(req, resp);
            return;
        }

        IUserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("User full name: " + user.getFullname());
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg =
                    "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.LOGIN).forward(req, resp);
        }
    }
}
