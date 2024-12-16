<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dao.DBConnection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="servlet.newsServlet"%>
<html>
<head>
<title>人工智能学院信息门户</title>
<link type="text/css" rel="styleSheet"  href="css/index.css" />
</head>
<body>
<jsp:include page="jsp/index/head.jsp"/>
<jsp:include page="jsp/index/slideshow.jsp"/>
	<div class="wrapper">
		<div class="layout_top">
			<div class="content_slides_show">
				<img src="img/content_slides_show/773692360EAF8A4DC8FD1E8AC57_7B8F3E4E_93CF.jpg" alt="" style="width: 100%; height: 100%;">
				<p>人工智能学院</p>
			</div>
				<div class="content_journalism">
					<table border="1">
						<tr>
							<th>新闻标题</th>
							<th>发布时间</th>
						</tr>
						<c:forEach var="news" items="${newsList}">
							<tr>
								<td>${news.title}</td>
								<td>${news.publishTime}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		</div>
		<div class="layout_middle"></div>
		<div class="layout_bottom">
			<div class="content_inform"></div>
			<div class="content_education"></div>
			<div class="content_learn"></div>
		</div>
	</div>
</div>
	<jsp:include page="jsp/index/foot.jsp"/>
</body>
</html>
