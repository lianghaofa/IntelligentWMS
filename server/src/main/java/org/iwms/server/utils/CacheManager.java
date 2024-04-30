package org.iwms.server.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.iwms.server.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author leung
 */
@Component
public class CacheManager {

    static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);
    private static final long GUAVA_CACHE_EXPIRE_MINUTES = 10; // Guava 缓存过期时间（分钟）

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Cache<String, Object> guavaCache = CacheBuilder.newBuilder()
            .maximumSize(1000) // 设置 Guava 缓存的最大容量
            .expireAfterWrite(GUAVA_CACHE_EXPIRE_MINUTES, TimeUnit.MINUTES) // 设置 Guava 缓存项的过期时间
            .build();

    public Object get(String key) {
        // 从 Guava 缓存中获取数据
        Object data = guavaCache.getIfPresent(key);
        if (data == null) {
            // 如果 Guava 缓存中没有数据，则从 Redis 缓存中获取
            // data = redisTemplate.opsForValue().get(key);
            if (data != null) {
                // 将数据放入 Guava 缓存
                guavaCache.put(key, data);
            }
            logger.info("get from Guava");
        }else {
            logger.info("get from Guava");
        }
        return data;
    }

    public void put(String key, Object value) {
        // 将数据放入 Guava 缓存
        guavaCache.put(key, value);
        // 将数据放入 Redis 缓存
        // redisTemplate.opsForValue().set(key, value);
    }

    public void delete(String key) {
        // 从 Guava 缓存中删除数据
        guavaCache.invalidate(key);
        // 从 Redis 缓存中删除数据
        // redisTemplate.delete(key);
    }

    public void clearGuavaCache() {
        // 清空 Guava 缓存
        guavaCache.invalidateAll();
    }

    public void clearRedisCache() {
        // 清空 Redis 缓存
        // redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}
