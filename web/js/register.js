function validateForm() {
    // 获取页面元素的值
    var username = document.getElementById("username").value; // 获取用户名输入框的值
    var password = document.getElementById("password").value; // 获取密码输入框的值
    var email = document.getElementById("email").value; // 获取电子邮件输入框的值
    var phoneNumber = document.getElementById("phone_number").value; // 获取电话号码输入框的值

    // 验证用户名是否为5-20个字母或数字
    if (!username.match(/^[a-zA-Z0-9]{5,20}$/)) {
        alert("用户名必须为5-20个字母或数字"); // 如果不符合条件，弹出提示框
        return false; // 返回false，阻止表单提交
    }
    // 验证密码长度是否至少为8个字符
    if (password.length < 8) {
        alert("密码至少需要8个字符"); // 如果不符合条件，弹出提示框
        return false; // 返回false，阻止表单提交
    }
    // 验证电子邮件格式是否正确
    if (!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/)) {
        alert("电子邮件格式不正确"); // 如果不符合条件，弹出提示框
        return false; // 返回false，阻止表单提交
    }
    // 验证电话号码格式是否正确
    if (!phoneNumber.match(/^[1]([3-9])[0-9]{9}$/)) {
        alert("电话号码格式不正确"); // 如果不符合条件，弹出提示框
        return false; // 返回false，阻止表单提交
    }
    // 如果所有验证都通过，返回true，允许表单提交
    return true;
}