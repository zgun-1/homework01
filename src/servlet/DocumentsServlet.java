package servlet;

import dao.DocumentDao;
import entity.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/news")
public class DocumentsServlet extends HttpServlet {
    private DocumentDao documentDao;

    public DocumentsServlet() {
        try {
            documentDao = new DocumentDao(); // 初始化 DocumentDao
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String category = request.getParameter("category");
        try {
            List<Document> documents;
            switch (category) {
                case "news":
                    documents = documentDao.getAllDocuments();
                    break;
                case "teach":
                    documents = documentDao.getDocumentsByCategory("教学科研");
                    break;
                case "learn":
                    documents = documentDao.getDocumentsByCategory("学工动态");
                    break;
                case "inform":
                default:
                    documents = documentDao.getDocumentsByCategory("通知公告"); // 默认加载通知公告
                    break;
            }
            // 限制结果为最新的10个文档
            documents = documents.size() > 10 ? documents.subList(0, 10) : documents;
            String newsHtml = generateNewsHtml(documents, category); // 生成新闻 HTML
            out.write(newsHtml); // 写入响应
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private String generateNewsHtml(List<Document> documents, String category) {
        StringBuilder newsHtml = new StringBuilder();
        for (Document document : documents) {
            String title;
            if ("news".equals(category)) {
                title = document.getTitle();
            } else {
                title = document.getTitle().length() > 15 ? document.getTitle().substring(0, 15) + "..." : document.getTitle();
            }
            newsHtml.append("<li class='documents-item'>\n")
                    .append("<a href='document.jsp?id=").append(document.getDocumentId()).append("' class='documents-title'>").append(title).append("</a>\n")
                    .append("<div class='documents-date'>[").append(document.getPublishTime()).append("]</div>\n")
                    .append("</li>\n");
        }
        return newsHtml.toString();
    }
}
