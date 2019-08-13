package com.br.minio.miniodemo.service.impl;


import com.br.minio.miniodemo.util.ResultList;
import com.br.minio.miniodemo.model.ObjectMessage;
import com.br.minio.miniodemo.service.ObjectService;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectServiceImpl implements ObjectService {

    @Resource
    private MinioClient minioClient;

    protected final Logger logger = LoggerFactory.getLogger(ObjectServiceImpl.class);

    /**
     * @param bucket
     * @return
     * @throws Exception
     */
    @Override
    public ResultList getObject(String bucket) throws Exception {
        List<ObjectMessage> list = new ArrayList<>();
        ObjectMessage objectMessage = null;
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucket);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                objectMessage = new ObjectMessage();
                objectMessage.setObjectName(item.objectName());
                objectMessage.setSize(item.size());
                objectMessage.setLastModified(item.lastModified());
                objectMessage.setIsDir(item.isDir());
                objectMessage.setStorageClass(item.storageClass());
                objectMessage.setEtag(item.etag());
                list.add(objectMessage);
            }
        } catch (Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        }
        ResultList rl = new ResultList();
        rl.setTotal(list.size());
        rl.setResult(list);
        return rl;
    }


    /**
     * @param bucket
     * @param object
     * @return
     */
    @Override
    public String downloadObject(String bucket, String object) throws Exception {
        String path = "";
        try {
            String basePath = System.getProperty("user.dir");
            path = StringUtils.join(new String[]{basePath,"temp",object}, File.separator);
            File tempFile = new File(path);



            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            minioClient.statObject(bucket,object);
            minioClient.getObject(bucket, object, path);


        } catch (Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        }
        System.out.println(path);
        return path;
    }

    /**
     * @param bucket
     * @param file
     * @return
     */
    @Override
    public String uploadObject(String bucket, MultipartFile file) throws Exception {
        String contentType = file.getContentType();
        InputStream inputStram = file.getInputStream();
        String fileName = file.getOriginalFilename().trim();
        try {
            minioClient.putObject(bucket, fileName, inputStram, inputStram.available(), contentType);
            return "Uploaded！";
        } catch (Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        }
    }

    /**
     * @param bucket
     * @param object
     * @param sout
     * @return
     * @throws Exception
     */
    @Override
    public String downloadFileObject(String bucket, String object, ServletOutputStream sout) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            InputStream is = minioClient.getObject(bucket, object);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(sout);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        } finally {
            try {
                if (bis != null){
                    bis.close();
                }
                if (bos != null){
                    bos.close();
                }
            } catch (Exception e) {
                logger.error("Error:", e.toString());
            }

        }
        return "SUCCESS！";
    }
}
