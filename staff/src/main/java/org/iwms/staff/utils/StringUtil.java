package org.iwms.staff.utils;

/**
 * @author leung
 */
public class StringUtil {

    // 将字符串的首字母大写
    public static String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static boolean isNotBlank(String str) {
        if (null == str){
            return false;
        }
        return !str.isEmpty();
    }
}
