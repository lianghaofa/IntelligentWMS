package org.iwms.server.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author leung
 */
public class GlobalCache {

    private static final Cache<String, Object> cache;

    static {
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        long maxCacheSize = maxHeapSize / 10;
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES) // 设置缓存项的过期时间
                .maximumSize(1000) // 设置缓存的最大容量
                .maximumWeight(maxCacheSize) // 设置缓存的最大权重，这里是10MB
                .build();
    }

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.getIfPresent(key);
    }

    public static void remove(String key) {
        cache.invalidate(key);
    }

    public static void clear() {
        cache.invalidateAll();
    }
}
