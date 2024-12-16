package servlet;

import dao.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "newsServlet", urlPatterns = {"/news"})
public class newsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> newsItems = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title, publish_time FROM documents ORDER BY publish_time DESC");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String date = sdf.format(resultSet.getTimestamp("publish_time")); // 使用Timestamp获取时间戳
                newsItems.add(new String[]{title, date});
            }

            request.setAttribute("newsItems", newsItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); // 确保路径正确
            dispatcher.forward(request, response);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving news items");
        }
    }
}