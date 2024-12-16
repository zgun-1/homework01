package entity;

import java.sql.Date;

public class Document {
    private int documentId;
    private String documentCategory;
    private String title;
    private Date publishTime;
    private int publisherId;
    private String content;
    private String author;

    // 构造函数
    public Document(int documentId, String documentCategory, String title, Date publishTime, int publisherId, String content, String author) {
        this.documentId = documentId;
        this.documentCategory = documentCategory;
        this.title = title;
        this.publishTime = publishTime;
        this.publisherId = publisherId;
        this.content = content;
        this.author = author;
    }

    // Getter 和 Setter 方法
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(String documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}