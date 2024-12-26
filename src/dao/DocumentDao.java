package dao;

import entity.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao {
    private Connection connection;

    public DocumentDao() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.connection = DBConnection.getConnection();
    }

    public Document getDocumentById(int documentId) throws SQLException {
        String sql = "SELECT * FROM documents WHERE document_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, documentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDocument(rs);
            }
        }
        return null;
    }

    public List<Document> getAllDocuments() throws SQLException {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                documents.add(mapResultSetToDocument(rs));
            }
        }
        return documents;
    }

    public List<Document> getDocumentsByCategory(String category) throws SQLException {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE document_category = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    documents.add(mapResultSetToDocument(rs));
                }
            }
        }
        return documents;
    }
    public List<Document> getAllCategory() throws SQLException {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents"; // 查询所有文档
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    documents.add(mapResultSetToDocument(rs));
                }
            }
        }
        return documents;
    }

    private Document mapResultSetToDocument(ResultSet rs) throws SQLException {
        return new Document(
                rs.getInt("document_id"),
                rs.getString("document_category"),
                rs.getString("title"),
                rs.getDate("publish_time"),
                rs.getInt("publisher_id"),
                rs.getString("content"),
                rs.getString("author")
        );
    }

    public void insertDocument(Document document) throws SQLException {
        String sql = "INSERT INTO documents (document_category, title, publisher_id, content, author) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, document.getDocumentCategory());
            stmt.setString(2, document.getTitle());
            stmt.setInt(3, document.getPublisherId());
            stmt.setString(4, document.getContent());
            stmt.setString(5, document.getAuthor());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                // 获取自动生成的键
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    // 将生成的ID分配给document对象，如果需要的话
                    document.setDocumentId(generatedId);
                }
                generatedKeys.close();
            }
        }
    }

    public void updateDocument(Document document) throws SQLException {
        String sql = "UPDATE documents SET document_category=?, title=?, publish_time=?, publisher_id=?, content=?, author=? WHERE document_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, document.getDocumentCategory());
            stmt.setString(2, document.getTitle());
            stmt.setDate(3, document.getPublishTime());
            stmt.setInt(4, document.getPublisherId());
            stmt.setString(5, document.getContent());
            stmt.setString(6, document.getAuthor());
            stmt.setInt(7, document.getDocumentId());
            stmt.executeUpdate();
        }
    }

    public void deleteDocument(int documentId) throws SQLException {
        String sql = "DELETE FROM documents WHERE document_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, documentId);
            stmt.executeUpdate();
        }
    }
}