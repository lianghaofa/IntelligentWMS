package org.iwms.driver.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String URL = "jdbc:mysql://192.168.10.99:3306/mom_dev_cq?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

    private static final String USERNAME = "root" ;

    private static final String PASSWORD = "root" ;

    public static Connection getConnection() {
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {

    }




}
