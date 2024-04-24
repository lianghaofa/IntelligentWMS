package org.iwms.objectstorage.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leung
 */
@Data
@Component("minioProperties")
@ConfigurationProperties(prefix = "spring.minio")
public class MinioProperties {
    /**
     * 连接端点
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
}
