package com;


import com.util.CreateSQLFile;
import com.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 测试
 */
public class App {
    public static void main(String[] args) {
        CreateSQLFile.createSQL();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入地区英文名（例：Beijing）");
            String sql = "select * from Region where RegionName = '" + sc.nextLine() + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("地区：" + resultSet.getString(1) +
                        " 确诊：" + resultSet.getString(2) +
                        " 治愈：" + resultSet.getString(3) +
                        " 死亡：" + resultSet.getString(4));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }


    }
}
