package com.br.minio.miniodemo.service;


import com.br.minio.miniodemo.util.Result;

public interface BucketService {

    /**
     * @return
     * @throws Exception
     */
    Result getBucket() throws Exception;

    /**
     * @param bucket
     * @return
     */
    Result addBucket(String bucket) throws Exception;
}
