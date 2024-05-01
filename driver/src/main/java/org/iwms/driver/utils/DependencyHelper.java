package org.iwms.driver.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author leung
 */
public class DependencyHelper {

    public static void getTableDependencies(Connection connection, String tableName) {

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SHOW CREATE TABLE" + tableName);

            if (resultSet.next()) {

                String createTableSql = resultSet.getString(2);

                System.out.println("Table: " + tableName);

                System.out.println("Create Table SQL: " + createTableSql);
            }
        }catch (SQLException e) {

        }
    }


}
