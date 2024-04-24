package org.iwms.server.service;

import org.iwms.server.WebApplication;
import org.iwms.server.utils.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author leung
 */
@Service
public class RedisService {

    static final Logger logger = LoggerFactory.getLogger(RedisService.class);


    @Autowired
    private CacheManager cacheManager;

    public void setValue(String key, String value) {
        cacheManager.put(key, value);
    }

    public Object getValue(String key) {
        return cacheManager.get(key);
    }
}
