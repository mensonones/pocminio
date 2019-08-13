package com.br.minio.miniodemo.service.impl;

import com.br.minio.miniodemo.util.Result;
import com.br.minio.miniodemo.util.ResultGenerator;
import com.br.minio.miniodemo.util.ResultList;
import com.br.minio.miniodemo.util.ResultMessage;
import com.br.minio.miniodemo.model.BucketMessage;
import com.br.minio.miniodemo.service.BucketService;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Resource
    private MinioClient minioClient;

    protected final Logger logger = LoggerFactory.getLogger(BucketServiceImpl.class);

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Result getBucket() throws Exception {
        List<BucketMessage> list = new ArrayList<>();
        BucketMessage bucketMessage = null;
        try {
            List<Bucket> bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                bucketMessage = new BucketMessage();
                bucketMessage.setName(bucket.name());
                bucketMessage.setCreationDate(bucket.creationDate());
                list.add(bucketMessage);
            }
        } catch (Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        }
        ResultList rl = new ResultList();
        rl.setTotal(list.size());
        rl.setResult(list);
        return ResultGenerator.genSuccessResult(rl, ResultMessage.SUCCESSGET);
    }



    /**
     * @param bucket
     * @return
     */
    @Override
    public Result addBucket(String bucket) throws Exception {
        try {
            minioClient.makeBucket(bucket);
            ResultList rl = ResultList.getResultList(1,1);
            return ResultGenerator.genSuccessResult(rl, ResultMessage.SUCCESSUPDATE);
        } catch(Exception e) {
            logger.error("Error: {}",e.toString());
            throw new Exception(e);
        }
    }
}
