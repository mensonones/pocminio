package com.br.minio.miniodemo.controllers;


import com.br.minio.miniodemo.config.CheckBucket;
import com.br.minio.miniodemo.util.Result;
import com.br.minio.miniodemo.util.ResultGenerator;
import com.br.minio.miniodemo.util.ResultMessage;
import com.br.minio.miniodemo.service.BucketService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/minio/bucket")
@CrossOrigin(origins = "http://localhost:8081")
public class BucketController {

    @Resource
    private BucketService bucketService;

    @Resource
    private CheckBucket checkBucket;


    /**
     * @return
     * @throws Exception
     */

    @GetMapping("")
    public Result getList() throws Exception{
        Result tmp = null;
        try {
            tmp = bucketService.getBucket();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return tmp;
    }

    /**
     * @param bucket
     * @return
     */
    @GetMapping("/add")
    public Result modifyBucket(@RequestParam String bucket) throws Exception{
        if(bucket == null || bucket.isEmpty()){
            return ResultGenerator.genFailResult(ResultMessage.INPUTSERROR);
        }
        if(checkBucket.isBucket(bucket)){
            return ResultGenerator.genFailResult(ResultMessage.UPDATEERROR+"："+bucket+"error！");
        }
        Result tmp = null;
        try {
            tmp = bucketService.addBucket(bucket);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return tmp;
    }
}
