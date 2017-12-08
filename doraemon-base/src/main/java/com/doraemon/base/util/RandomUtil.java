package com.doraemon.base.util;

import java.util.Random;

/**
 * Created by zbs on 2017/6/5.
 */
public class RandomUtil {


    private static  String[] letterAndNum = {
            "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9",
    };

    private  static String base = "abcdefghijklmnopqrstuvwxyz0123456789";

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


    /**
     * 生成随机的字母加数字的串
     * @param length
     * @return
     * @throws Exception
     */
    public static String getRandomLetterAndNum(int length) throws Exception {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(letterAndNum[r.nextInt(letterAndNum.length)]);
        }
        return sb.toString();
    }

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
