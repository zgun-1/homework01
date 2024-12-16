package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {
    static String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";    //驱动路径
    // JDBC驱动名和数据库URL
    private static final String DB_URL = "jdbc:mysql://localhost:3308/homework"; // 根据需要添加参数

    // 数据库的用户名与密码，需要根据自己的数据库设置进行更改
    private static final String USER = "root";
    private static final String PASS = "123456";

    // 获取数据库连接的方法
    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName(DRIVER_PATH).newInstance();
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // 测试代码（通常不会放在这里，但为了演示目的而包含）
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        Class.forName(DRIVER_PATH).newInstance();
        Statement stmt = null;
        try {
            // 获取数据库连接
            conn = DBConnection.getConnection();

            // 创建statement对象来执行查询
            stmt = conn.createStatement();
            String sql = "SELECT user_id, username FROM users"; // 替换为您的表名
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集并打印
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("user_id");
                String name = rs.getString("username");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.println(", Name: " + name);
            }
            // 完成后关闭

        } catch (SQLException se) {
            // 处理JDBC错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理Class.forName错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}