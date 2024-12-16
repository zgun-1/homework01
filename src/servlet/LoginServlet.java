package servlet;

import dao.UserDao;
import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernameAndPassword(username, password);

        if (user != null) {
            // 登录成功，重定向到欢迎页面或主页
            response.sendRedirect("index.jsp"); // 假设欢迎页面是index.jsp
        } else {
            // 登录失败，重定向回登录页面并显示错误信息
            response.sendRedirect("login.jsp?error=invalidCredentials");
        }
    }
}