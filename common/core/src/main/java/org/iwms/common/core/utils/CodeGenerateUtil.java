package org.iwms.common.core.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author leung
 */
public class CodeGenerateUtil {


    // 创建一个带有固定大小的缓存，最大容量为 1000，过期时间为 5 分钟
    Cache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    public static String generateCodeByTimeMillis(String pre){
        long timestamp = System.currentTimeMillis();
        return pre + timestamp;
    }

    public static String generateCodeByDate(String pre){
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return pre + sdf.format(date);
    }
    public static void main(String[] args) {
        System.out.println(generateCodeByTimeMillis("CQSWO"));
        System.out.println(generateCodeByDate("CQSWO"));
    }



}
