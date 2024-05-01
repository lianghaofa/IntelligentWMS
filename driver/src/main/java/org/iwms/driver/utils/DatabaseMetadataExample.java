package org.iwms.driver.utils;

import java.sql.*;
import java.util.*;

/**
 * @author leung
 */
public class DatabaseMetadataExample {

    public static void main(String[] args) {

        try (Connection connection = DBHelper.getConnection()) {

            // 1
            List<String> allTables = TableHelper.getAllTables(connection);

            //
            // 存储表之间的依赖关系
            Map<String, List<String>> dependencies = new HashMap<>();
            for (String tableName : allTables){

//                ForeignKeyHelper.getTableForeignKeys(connection, table);
                try {
                    DatabaseMetaData metaData = connection.getMetaData();
                    ResultSet resultSet = metaData.getImportedKeys(null, null, tableName);
                    while (resultSet.next()){
                        String pkTable = resultSet.getString("PKTABLE_NAME");
                        String pkColumn = resultSet.getString("PKCOLUMN_NAME");
                        String fkTable = resultSet.getString("FKTABLE_NAME");
                        String fkColumn = resultSet.getString("FKCOLUMN_NAME");
                        dependencies.computeIfAbsent(fkTable, k -> new ArrayList<>()).add(pkTable);
                        // System.out.println("Foreign key: " + fkTable + "." + fkColumn + " references " + pkTable + "." + pkColumn);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }

            // 使用拓扑排序获取表的执行顺序
            List<String> executionOrder = TableDependencyOrder.getOrder(dependencies);


            // 输出执行顺序
            Set<String> orderSet = new HashSet<>();
            System.out.println("Execution order:");
            int i = 0;
            for (String tableName : executionOrder) {

                if (orderSet.contains(tableName)){
                    //
                    System.out.println("存在重复的表：" + tableName);
                }else {
                    System.out.println((i ++) + "," + tableName);
                    orderSet.add(tableName);
                }

            }
            System.out.println("表的个数：" + orderSet.size());


            // 使用拓扑排序获取表的执行顺序
            // List<String> executionOrder = getOrder(dependencies);

            // 输出执行顺序
//            System.out.println("Execution order:");
//            for (String tableName : executionOrder) {
//                System.out.println(tableName);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
