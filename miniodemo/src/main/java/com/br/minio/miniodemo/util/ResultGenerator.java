package com.br.minio.miniodemo.util;


public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final String DEFAULT_FAIL_MESSAGE = "failed";

    /**
     * @return result
     */
    public static Result genSuccessResult() {
        return new Result().setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * @param data
     * @param message
     * @return result
     */
    public static Result genSuccessResult(Object data,String message) {
        return new Result().setStatus(DEFAULT_SUCCESS_MESSAGE)
                .setMessage(message)
                .setData(data);
    }

    /**
     * @param message
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result().setMessage(message)
                .setStatus(DEFAULT_FAIL_MESSAGE);
    }

}
