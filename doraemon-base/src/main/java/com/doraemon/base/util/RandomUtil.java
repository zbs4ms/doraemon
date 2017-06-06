package com.doraemon.base.util;

import java.util.Random;

/**
 * Created by zbs on 2017/6/5.
 */
public class RandomUtil {

    /**
     * 获取任意个中文字符串
     * @param length
     * @return
     * @throws Exception
     */
    public static String getRandomGBK(int length) throws Exception {
        if(length == 0)
            return null;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        while (length != 0) {
            int hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
            int lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            sb.append(new String(b, "GBk"));//转成中文
            length--;
        }
        return sb.toString();
    }

}
