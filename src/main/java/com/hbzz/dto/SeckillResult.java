package com.hbzz.dto;

/**
 * 封装JSON结果
 * 所有ajax请求放回类型，封装json结果
 */
public class SeckillResult<T> {

    private boolean success; //是否

    private T data;  //时间

    private String error;//错误信息

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
