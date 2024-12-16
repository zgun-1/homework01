<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>新闻详情</title>
    <link type="text/css" rel="styleSheet" href="css/index.css" />
</head>
<body>
<div class="content_journalism">
    <h2>新闻详情</h2>
    <%
        NewsDetailServlet.NewsDetail newsDetail = (NewsDetailServlet.NewsDetail) request.getAttribute("newsDetail");
        if (newsDetail != null) {
    %>
    <h3><%= newsDetail.getTitle() %></h3>
    <div class="news-content">
        <%= newsDetail.getContent() %>
    </div>
    <% } else { %>
    <p>News not found or an error occurred.</p>
    <% } %>
</div>
</body>
</html>