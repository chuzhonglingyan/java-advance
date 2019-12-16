package com.yuntian.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * @Auther: yuntian
 * @Date: 2019/12/16 0016 20:26
 * @Description:
 */
public class DBManager {

    private static String URL;
    private static String USER_NAME;
    private static String PASS_WORD;

    static {
        //配置文件读取
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        URL = bundle.getString("URL");
        USER_NAME = bundle.getString("USER_NAME");
        PASS_WORD = bundle.getString("PASS_WORD");
        try {
            String jdbcDriver = bundle.getString("JDBC_DRIVER");
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < ConnectionPool.MIN_SIZE; i++) {
            ConnectionPool.add(DBManager.getConn());
        }
    }


    /**
     * 获取连接对象
     *
     * @return Connection连接对象
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    /**
     * 获取连接对象
     *
     * @return Connection连接对象
     */
    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        return connection.prepareStatement(sql);
    }


    /**
     * 关闭连接（Connection连接对象必须在最后关闭）
     *
     * @param st 编译执行对象
     * @param rs 结果集
     */
    public static void close(Connection connection, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                ConnectionPool.add(connection);
            }
        }
    }

    public static void close(Connection connection, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                ConnectionPool.add(connection);
            }
        }
    }


    public static void close() {
        Queue<Connection> queue = ConnectionPool.getConnectionList();
        while (!queue.isEmpty()) {
            Connection connection = queue.remove();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        queue.clear();
    }


}
