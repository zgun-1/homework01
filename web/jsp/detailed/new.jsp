<%@ page import="dao.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@ page pageEncoding="utf-8"%>
<%
    String id = request.getParameter("id");
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        conn = DBConnection.getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM Documents WHERE document_id='" + id + "'";
        rs = stmt.executeQuery(sql);

        if (rs.next()) {
            String title = rs.getString("title");
            Date publishTime = rs.getDate("publish_time");
            String content = rs.getString("content"); // 假设有一个content字段
            out.println("Title: " + title);
            out.println("<div class='news-detail'>\n");
            out.println("<h1>" + title + "</h1>\n");
            out.println("<div class='news-date'>[" + publishTime + "]</div>\n");
            out.println("<div class='news-content'>" + content + "</div>\n");
            out.println("</div>\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>