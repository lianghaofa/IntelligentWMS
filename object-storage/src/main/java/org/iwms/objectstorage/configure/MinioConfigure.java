package org.iwms.objectstorage.configure;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leung
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfigure {

    private final MinioProperties properties;

    public MinioConfigure(@Qualifier("minioProperties") MinioProperties properties) {
        this.properties = properties;
    }

    /**
     * 构建客户端
     *
     * @return MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
