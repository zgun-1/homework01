package servlet;

import dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
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
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, email, gender, birthday, phone_number, address, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // 注意：实际应用中应该使用加密存储密码
            pstmt.setString(3, email);
            pstmt.setString(4, gender);
            pstmt.setDate(5, birthday);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, address);
            pstmt.executeUpdate();
            // 注册成功后，重定向到登录页面，并带上用户名
            response.sendRedirect("jsp/login/login.jsp?username=" + username);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/login/register.jsp?error=true");
        }
    }
}