package com.doraemon.base.exceptions;

import lombok.Data;

/**
 * 所有需要打印到页面的报错
 * Created by zbs on 2017/9/12.
 */
@Data
public class ShowExceptions extends RuntimeException {

    public Integer code;

    public ShowExceptions() {
        super();
    }

    public ShowExceptions(String msg, Integer code) {
        super(msg);
        this.setCode(code);
    }

    public ShowExceptions(String msg) {
        super(msg);
    }
}
