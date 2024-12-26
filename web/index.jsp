<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="dao.DBConnection" %>
<%@ page import="java.sql.ResultSet" %>

<html>
<head>
	<title>人工智能学院信息门户</title>
	<link type="text/css" rel="styleSheet"  href="css/index.css" />

	<script>
		function loadDocuments(category) {
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var content = xhr.responseText;
					switch (category) {
						case 'news':
							document.getElementById("newsList").innerHTML = content;
							break;
						case 'inform':
							document.getElementById("informList").innerHTML = content;
							break;
						case 'teach':
							document.getElementById("teachList").innerHTML = content;
							break;
						case 'learn':
							document.getElementById("learnList").innerHTML = content;
							break;
					}
				}
			};
			xhr.open("GET", "${pageContext.request.contextPath}/news?category=" + category, true);
			xhr.send();
		}

		window.onload = function() {
			loadDocuments('news');
			loadDocuments('inform'); // 加载通知公告
			loadDocuments('teach'); // 加载教学科研
			loadDocuments('learn'); // 加载学工动态
		};
	</script>

	<style>
		#newsList, #teachList, #learnList, #informList {
			list-style-type: none; /* 移除列表项前的标记 */
			padding: 0; /* 移除内边距 */
		}

		.documents-item {
			display: flex;
			justify-content: space-between; /* 标题和时间分别靠左右两端 */
			align-items: center; /* 垂直居中 */
			border-bottom: none; /* 去除下边框 */
			padding: 0;
			margin: 0;
			font-family: "等线 Light	", sans-serif;
			font-weight: normal; /* 设置文本不使用加粗 */
			height: 20px;
			}

		.documents-title {
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
			height: 15px;
			font-size: 10px; /* 字体大小 */
			color: #666; /* 字体颜色 */
			text-decoration: none; /* 去掉下划线 */
			font-weight: normal; /* 设置文本不使用加粗 */
		}
		.documents-title:hover {
			text-decoration: underline; /* 鼠标悬停时的下划线 */
			color: red;
		}


		.documents-date {
			color: #666; /* 时间颜色 */
			font-size: 10px;
			white-space: nowrap; /* 防止时间换行 */
			height: 15px;
			margin-left: 30px;
			font-family: "等线 Light	", sans-serif;
			font-weight: normal; /* 设置文本不使用加粗 */
		}
	</style>
</head>
<body>
<jsp:include page="jsp/public/head.jsp"/>
<jsp:include page="jsp/public/slideshow.jsp"/>
	<div class="wrapper">
		<div class="layout_top">
			<div class="content_slides_show">
				<img src="img/content_slides_show/773692360EAF8A4DC8FD1E8AC57_7B8F3E4E_93CF.jpg" alt="" style="width: 100%; height: 100%;">
				<p>人工智能学院</p>
			</div>
			<div class="content_journalism">
				<div class="module_title">
					<div class="notice-container">
						<img class="notice-icon" src="img/icon/img.png" alt="通知图标" width="24" height="24">
						<div class="notice-title">学院新闻</div>
					</div>
					<div class="more-show"><a href="#" class="more-link">更多></a></div>
				</div>
				<ul id="newsList"></ul>
			</div>


		</div>
		<div class="layout_middle">
			<div class="module_title">
				<div class="notice-container">
					<img class="notice-icon" src="icon.png" alt="通知图标" width="24" height="24">
					<div class="notice-title">学子风采</div>
				</div>
				<div class="more—show"><a href="#" class="more-link">更多></a></div>
			</div>
		</div>

		<div class="layout_bottom">
			<div class="content">
				<div class="module_title">
					<div class="notice-container">
						<img class="notice-icon" src="img/icon/tz.png" alt="通知图标" width="24" height="24">
						<div class="notice-title">通知公告</div>
					</div>
					<div class="more—show"><a href="#" class="more-link">更多></a></div>
				</div>
				<div><ul id="informList"></ul></div>
			</div>
			<div class="content">
				<div class="module_title">
					<div class="notice-container">
						<img class="notice-icon" src="img/icon/ky.png" alt="通知图标" width="24" height="24">
						<div class="notice-title">教学科研</div>
					</div>
					<div class="more—show"><a href="#" class="more-link">更多></a></div>
				</div>
				<div><ul id="teachList"></ul></div>
			</div>
			<div class="content">
				<div class="module_title">
					<div class="notice-container">
						<img class="notice-icon" src="img/icon/xs.png" alt="通知图标" width="24" height="24">
						<div class="notice-title">学工动态</div>
					</div>
					<div class="more—show"><a href="#" class="more-link">更多></a></div>
				</div>
				<div><ul id="learnList"></ul></div>

			</div>
		</div>
</div>
	<jsp:include page="jsp/public/foot.jsp"/>
</body>
</html>
