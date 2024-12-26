<%@ page import="dao.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@ page pageEncoding="utf-8"%>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .container {
        width: 80%;
        margin: 20px auto;
        background: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .news-detail {
        margin-bottom: 20px;
    }
    .news-title {
        color: #333;
        font-size: 24px;
        margin-bottom: 10px;
    }
    .news-meta {
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: #666;
        font-size: 14px;
        margin-bottom: 10px;
    }
    .news-meta .news-category {
        font-weight: bold;
        color: #4CAF50; /* Green */
    }
    .news-meta .news-separator {
        margin: 0 10px;
        color: #999;
    }
    .news-meta .news-author {
        margin-left: 10px;
    }
    .news-content {
        font-size: 16px;
        line-height: 1.6;
        color: #444;
    }
</style>
<body>
<jsp:include page="jsp/public/head.jsp"/>
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
            String content = rs.getString("content");
            String category = rs.getString("document_category");
            String author = rs.getString("publisher_id");

            out.println("<div class='container'>\n");
            out.println("<div class='news-detail'>\n");
            out.println("<h1 class='news-title'>" + title + "</h1>\n");
            out.println("<div class='news-meta'>\n");
            out.println("<span class='news-category'>类型: " + category + "</span>\n");
            out.println("<span>发布时间: [" + publishTime + "] <span class='news-separator'>|</span> 作者: " + author + "</span>\n");
            out.println("</div>\n");
            out.println("<div class='news-content'>" + content + "</div>\n");
            out.println("</div>\n");
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
<jsp:include page="jsp/public/foot.jsp"/>
</body>