package com.doraemon.base.util.bean;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * Created by zbs on 2017/6/27.
 */
@Data
public class CaptchaData {
        String code;
        BufferedImage image;
}
