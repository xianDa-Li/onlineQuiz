package com.lixianda.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;


/*
    JDBC工具类，简化JDBC编程
 */
public class DBUtil {

    /*
        工具类当中的构造方法都是私有的。
        因为工具类当中的方法都是静态的，不需要new对象，直接采用类名调用。
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 通过全局作用域对象得到Connection--------------start
    public static Connection getConnection(HttpServletRequest request){
        Connection conn = null;
        //1.通过请求对象，得到全局作用域对象
        ServletContext application = request.getServletContext();
        //2.从全局作用域对象得到map
        Map map = (Map)application.getAttribute("key1");
        //3.从map中得到一个处于空闲状态的Connection
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            conn = (Connection)it.next();
            boolean flag = (boolean)map.get(conn);
            if(flag == true){
                break;
            }
        }
        return conn;
    }
    // 通过全局作用域对象得到Connection--------------start

    private DBUtil() {
    }

    /*
        获取数据库连接对象
        @return 连接对象
        @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC", "root", "qqlyk001");
        return conn;
    }

    /*
        关闭资源
        @param conn 连接对象
        @param ps   数据库操作对象
        @param rs   结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
