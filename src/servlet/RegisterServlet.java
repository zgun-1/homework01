package servlet;

import dao.DBConnection;
import dao.UserDao;
import entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthdayStr = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phone_number");
        String address = request.getParameter("address");

        // 输入验证
        if (!username.matches("^[a-zA-Z0-9]{5,20}$")) {
            response.sendRedirect("jsp/login/register.jsp?error=invalidUsername");
            return;
        }
        if (password.length() < 8) {
            response.sendRedirect("jsp/login/register.jsp?error=invalidPassword");
            return;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            response.sendRedirect("jsp/login/register.jsp?error=invalidEmail");
            return;
        }
        if (!phoneNumber.matches("^[1]([3-9])[0-9]{9}$")) {
            response.sendRedirect("jsp/login/register.jsp?error=invalidPhone");
            return;
        }

        try {
            User user = new User(username, password, email, gender, Date.valueOf(birthdayStr), phoneNumber, address);
            UserDao userDao = new UserDao();
            userDao.insertUser(user);
            // 注册成功后，重定向到登录页面

            // 注册成功后，重定向到登录页面，并带上用户名和密码
            response.sendRedirect("jsp/login/login.jsp?username=" + username + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/login/register.jsp?error=true");
        }
    }
}