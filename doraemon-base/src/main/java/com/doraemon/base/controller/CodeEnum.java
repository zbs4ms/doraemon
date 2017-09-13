package com.doraemon.base.controller;

/**
 * Created by zbs on 2017/9/13.
 */
public enum CodeEnum {

    SUCCESS(200, "成功."),
    FAILURE_NO_PERMISSIONS(401, "失败,没有权限."),
    FAILURE_SHOW_MSG(406,"失败,显示异常信息到页面上."),
    FAILURE_UNKNOWN_ERROR(500,"未知错误."),
    FAILURE_ERROR(400,"请求出错.");

    private Integer code;
    private String details;

    CodeEnum(Integer code,String details){
        this.code = code;
        this.details = details;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
