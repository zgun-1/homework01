<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <link rel="stylesheet" href="../../css/login/login.css">
    <script src="../../js/register.js" defer></script>
</head>
<body>
<div class="auth-container">
    <form action="${pageContext.request.contextPath}/registerServlet" method="post" class="auth-form" onsubmit="return validateForm()">
        <h2>注册</h2>
        <div class="input-group">
            <label><span>用&nbsp;&nbsp;户&nbsp;&nbsp;名</span></label>
            <input type="text" id="username" name="username" required placeholder="5-20个字母或数字">
        </div>
        <div class="input-group">
            <label><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span></label>
            <input type="password" id="password" name="password" required placeholder="至少8个字符">
        </div>
        <div class="input-group">
            <label><span>电子邮件</span></label>
            <input type="email" id="email" name="email" required placeholder="example@example.com">
        </div>
        <div class="input-group">
            <label><span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span></label>
            <select id="gender" name="gender" required>
                <option value="">请选择</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div class="input-group">
            <label><span>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</span></label>
            <input type="date" id="birthday" name="birthday" required>
        </div>
        <div class="input-group">
            <label><span>电话号码</span></label>
            <input type="tel" id="phone_number" name="phone_number" required placeholder="11位数字，以1开头">
        </div>
        <div class="input-group">
            <label><span>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</span></label>
            <input type="text" id="address" name="address" required placeholder="您的居住地址">
        </div>
        <button type="submit">注册</button>
    </form>
</div>
</body>
</html>