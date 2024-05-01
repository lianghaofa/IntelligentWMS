package org.iwms.driver.utils;

import java.util.*;

/**
 * @author leung
 */
public class TableDependencyOrder {

    public static void main(String[] args) {
        // 假设这是你的表之间的依赖关系
        Map<String, List<String>> dependencies = new LinkedHashMap<>();
        dependencies.put("table1", Arrays.asList("table2", "table3", "table4"));
        dependencies.put("table2", Collections.singletonList("table3"));
        dependencies.put("table3", Arrays.asList("table4"));
        dependencies.put("table4", Collections.emptyList());
        List<String> executionOrder = getOrder(dependencies);

        System.out.println("Execution order:");
        for (String tableName : executionOrder) {
            System.out.println(tableName);
        }
    }

    public static List<String> getOrder(Map<String, List<String>> dependencies) {
        List<String> executionOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String tableName : dependencies.keySet()) {
            dfs(tableName, dependencies, visited, executionOrder);
        }

        return executionOrder;
    }

    private static void dfs(String tableName, Map<String, List<String>> dependencies, Set<String> visited, List<String> executionOrder) {
        if (!visited.contains(tableName)) {
            visited.add(tableName);
            for (String dependentTable : dependencies.get(tableName)) {
                dfs(dependentTable, dependencies, visited, executionOrder);
            }
            executionOrder.add(tableName);
        }
    }




}
