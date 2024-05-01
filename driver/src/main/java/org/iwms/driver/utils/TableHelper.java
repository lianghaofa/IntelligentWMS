package org.iwms.driver.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leung
 */
public class TableHelper {


    public static List<String> getAllTables(Connection connection) {
        List<String> result = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                result.add(tableName);
                System.out.println("Table: " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
