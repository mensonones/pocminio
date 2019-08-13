package com.br.minio.miniodemo.config;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.server.endpoint}")
    private String endpoint;
    @Value("${minio.server.accessKey}")
    private String accessKey;
    @Value("${minio.server.secreKey}")
    private String secreKey;

    protected final Logger logger = LoggerFactory.getLogger(MinioConfig.class);

    @Bean(name = "minioClient")
    public MinioClient minioClient() {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(endpoint, accessKey,secreKey);
        } catch (Exception e) {
            logger.error("minio:{}Error！",endpoint);
        }
        return minioClient;
    }


}