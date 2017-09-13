package com.doraemon.base.controller.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by zbs on 2017/9/12.
 */
@Data
public class LoginMessage {

    public String userId;
    public Date startTime;
    public Date stopTime;
}
