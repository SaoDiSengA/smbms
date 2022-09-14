package servlet.user;

import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login=====================");
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(userCode,userPassword);
            if (null != user){
                req.getSession().setAttribute(Constants.USER_SESSION,user);
                resp.sendRedirect("jsp/frame.jsp");
            }else {
                req.setAttribute("error","用户不存在或密码不正确");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
