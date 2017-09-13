package com.doraemon.base.exceptions;

/**
 * 所有需要打印到页面的报错
 * Created by zbs on 2017/9/12.
 */
public class ShowExceptions extends RuntimeException {

    public ShowExceptions() {
        super();
    }

    public ShowExceptions(String s) {
        super(s);
    }
}
