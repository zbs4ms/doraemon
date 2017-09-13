package com.doraemon.base.util;

import lombok.extern.log4j.Log4j;

/**
 * Created by zbs on 2017/9/12.
 */
@Log4j
public class VerifyParameter {

    /**
     * 字符串不为""或null
     * @param string 待校验字符串
     * @return
     */
    public static boolean notNullAndEmpty(String string) {
        boolean flag = false;
        try {
            flag = notNullAndEmpty(string, false);
        }catch (Exception e){
            log.error(e.getMessage());
            flag = false;
        }
        return flag;
    }

    /**
     * 字符串不为""或null
     * @param string  待校验字符串
     * @param throwException 是否抛异常 true 抛  false 不抛
     * @return
     * @throws Exception
     */
    public static boolean notNullAndEmpty(String string,boolean throwException) throws Exception {
        boolean flag = true;
        if (string == null || "".equals(string))
            flag = false;
        if(!flag && !throwException)
            throw new Exception("参数为空.");
        return flag;
    }
}
