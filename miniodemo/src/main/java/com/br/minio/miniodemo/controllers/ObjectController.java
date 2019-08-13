package com.br.minio.miniodemo.controllers;

import com.br.minio.miniodemo.config.CheckBucket;
import com.br.minio.miniodemo.util.Result;
import com.br.minio.miniodemo.util.ResultGenerator;
import com.br.minio.miniodemo.util.ResultList;
import com.br.minio.miniodemo.util.ResultMessage;
import com.br.minio.miniodemo.service.ObjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/minio/object")
@CrossOrigin(origins = "http://localhost:8081")
public class ObjectController {

    @Resource
    private ObjectService objectService;

    @Resource
    private CheckBucket checkBucket;


    /**
     * @param bucket
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public Result getList(@RequestParam String bucket) throws Exception{
        if(bucket == null || bucket.isEmpty()){
            return ResultGenerator.genFailResult(ResultMessage.INPUTSERROR);
        }
        if(!checkBucket.isBucket(bucket)){
            return ResultGenerator.genFailResult(ResultMessage.GETNULL+"："+bucket+"error！");
        }
        ResultList tmp = null;
        try {
            tmp = objectService.getObject(bucket);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResultGenerator.genSuccessResult(tmp,ResultMessage.SUCCESSGET);
    }

    /**
     * @param bucket
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam String bucket,@RequestParam(value="file") MultipartFile file) throws Exception{
        if(bucket == null || bucket.isEmpty()){
            return ResultGenerator.genFailResult(ResultMessage.INPUTSERROR);
        }
        if(file.isEmpty()){
            return ResultGenerator.genFailResult("fail");
        }
        if(!checkBucket.isBucket(bucket)){
            return ResultGenerator.genFailResult(ResultMessage.GETNULL+"："+bucket+"error！");
        }
        String tmp = null;
        try {
            tmp = objectService.uploadObject(bucket,file);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResultGenerator.genSuccessResult(null,tmp);
    }

    /**
     * @param response
     * @return
     */
    @GetMapping("/downloadFile")
    public Result downloadFile(@RequestParam String bucket,@RequestParam String object,HttpServletResponse response) throws Exception {
        if(bucket == null || bucket.isEmpty() || object == null || object.isEmpty() ){
            return ResultGenerator.genFailResult(ResultMessage.INPUTSERROR);
        }
        if(!checkBucket.isBucket(bucket)){
            return ResultGenerator.genFailResult(ResultMessage.GETNULL+"："+bucket+"error！");
        }
        String tmp = "";
        try {
            response.reset();
            response.setHeader("Access-Control-Allow-Origin", "Origin");
            response.setContentType("application/*;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((object).getBytes(), "iso-8859-1"));
            ServletOutputStream sout = response.getOutputStream();
            tmp = objectService.downloadFileObject(bucket,object,sout);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResultGenerator.genSuccessResult(null,tmp);
    }
}
