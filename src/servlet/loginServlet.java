package servlet;

import dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 直接从DBConnection获取数据库连接
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // 注意：实际应用中应该使用加密存储密码
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 登录成功，重定向到欢迎页面或主页
                response.sendRedirect("index.jsp"); // 假设欢迎页面是index.jsp
            } else {
                // 登录失败，重定向回登录页面并显示错误信息
                response.sendRedirect("jsp/login/login.jsp?error=invalidCredentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/login/login.jsp?error=true");
        } finally {
            // 关闭数据库连接
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}