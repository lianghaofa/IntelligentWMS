package org.iwms.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author leung
 */
public class BCPEUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     *  将原生密码进行加密
     * @return 加密后的字符串密码
     */
    public static String encode(String rawPWD){
        String hashedPassword = passwordEncoder.encode(rawPWD);
        return hashedPassword;
    }

    /**
     * 比较原生密码与密文是否一致
     * @param rawPWD 原生密码
     * @param encodePWD 密文
     * @return true/false
     */
    public static boolean matches(String rawPWD,String encodePWD){
        boolean result = passwordEncoder.matches(rawPWD, encodePWD);
        return result;
    }


    public static void main(String[] args) {
        String password="123456";
        String rePassword=BCPEUtils.encode(password);
        System.out.println(rePassword);
        System.out.println("----------------------");
        System.out.println(BCPEUtils.matches(password,rePassword));
    }
}
