package com.br.minio.miniodemo.util;

import com.alibaba.fastjson.JSON;

public class Result {
    private String status;
    private String message;
    private Object data;

    /**
     *
     * @param mes
     * @return result
     */
    public Result setMessage(String mes) {
        this.message = mes;
        return this;
    }

    /**
     *
     * @return messsage
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     *
     * @param data
     * @return result
     */
    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     *
     * @param status
     * @return result
     */
    public Result setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
