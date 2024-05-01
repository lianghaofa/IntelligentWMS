package org.iwms.driver.utils;

public class StringUtil {

    // 将字符串的首字母大写
    public static String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
