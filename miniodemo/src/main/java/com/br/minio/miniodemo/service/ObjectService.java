package com.br.minio.miniodemo.service;


import com.br.minio.miniodemo.util.ResultList;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;

public interface ObjectService {

    ResultList getObject(String bucket) throws Exception;

    /**
     * @param bucket
     * @param object
     * @return
     */
    String downloadObject(String bucket, String object) throws Exception;

    /**
     * @param bucket
     * @param file
     * @return
     */
    String uploadObject(String bucket, MultipartFile file) throws Exception;

    /**
     * @param bucket
     * @param object
     * @param sout
     * @return
     */
    String downloadFileObject(String bucket, String object, ServletOutputStream sout) throws Exception;
}
