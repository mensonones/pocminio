package com.br.minio.miniodemo.config;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CheckBucket {

    @Resource
    private MinioClient minioClient;

    protected final Logger logger = LoggerFactory.getLogger(CheckBucket.class);

    /**
     * @param bucket
     * @return
     */
    public boolean isBucket(String bucket){
        try {
            return minioClient.bucketExists(bucket);
        } catch (Exception e) {
            logger.error("Error："+e.toString());
        }
       return false;

    }


}
