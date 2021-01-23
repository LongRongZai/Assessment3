/**
 * JDBC工具
 */
package com.util;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    private JDBCUtil() {
    }

    /**
     *注册驱动
     */
    static {
        try {
            Class.forName(bundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return con
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     * @param con
     * @param stm
     */
    public static void close(Connection con, Statement stm, ResultSet res) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (res != null) {
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
