<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>登录界面</title>
  <link rel="stylesheet" href="../../css/login/login.css">
</head>
<body>
<div class="auth-container">
  <form action="${pageContext.request.contextPath}/loginServlet" method="post" class="auth-form">
    <h2>登录</h2>
    <div class="input-group">
      <label><span>用户名</span></label>
      <input type="text" id="username" name="username" required>
    </div>
    <div class="input-group">
      <label><span>密&nbsp;&nbsp;码</span></label>
      <label for="password"></label><input type="password" id="password" name="password" required>
    </div>
    <button type="submit">登录</button>
    <p>没有账号？<a href="register.jsp">注册</a></p>
  </form>
</div>
</body>
</html>