package com.doraemon.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zbs on 2017/6/5.
 */
public class StringReplace {

    /**
     * 按照{0},{1},{2} 用array中的值进行替换
     * @param str
     * @param array
     * @return
     */
    public static String findAndReplace(String str,String ... array){
        Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
        while (m.find()) {
            str=str.replace(m.group(),array[Integer.parseInt(m.group(1))]);
        }
        return str;
    }
}
