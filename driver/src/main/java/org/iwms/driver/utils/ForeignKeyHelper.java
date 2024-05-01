package org.iwms.driver.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author leung
 */
public class ForeignKeyHelper {

    public static void getTableForeignKeys(Connection connection, String tableName){
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getImportedKeys(null, null, tableName);
            while (resultSet.next()){
                String pkTable = resultSet.getString("PKTABLE_NAME");
                String pkColumn = resultSet.getString("PKCOLUMN_NAME");
                String fkTable = resultSet.getString("FKTABLE_NAME");
                String fkColumn = resultSet.getString("FKCOLUMN_NAME");
                System.out.println("Foreign key: " + fkTable + "." + fkColumn + " references " + pkTable + "." + pkColumn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
