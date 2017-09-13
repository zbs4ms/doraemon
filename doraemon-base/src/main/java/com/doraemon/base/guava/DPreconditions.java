package com.doraemon.base.guava;

import com.doraemon.base.exceptions.ShowExceptions;

/**
 * Created by zbs on 2017/9/13.
 */
public class DPreconditions {

    private DPreconditions(){}

    private static void throwException(Object errorMessage,boolean showPage){
        if(showPage)
            throw new ShowExceptions(String.valueOf(errorMessage));
        throw new IllegalArgumentException(String.valueOf(errorMessage));
    }

    private static void throwNullPointerException(Object errorMessage,boolean showPage){
        if(showPage)
            throw new ShowExceptions(String.valueOf(errorMessage));
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    /**
     * 校验参数
     * @param expression
     */
    public static void checkArgument(boolean expression) {
        checkArgument(expression,"",false);
    }
    public static void checkArgument(boolean expression, Object errorMessage) {
        checkArgument(expression,errorMessage,false);
    }
    public static void checkArgument(boolean expression, Object errorMessage,boolean showPage) {
        if (!expression)
                throwException(errorMessage,showPage);
    }
    public static void checkArgument(boolean expression, String errorMessageTemplate,boolean showPage, Object... errorMessageArgs) {
        if (!expression) {
            if(errorMessageArgs == null){
                throwException(errorMessageTemplate,showPage);
            }else{
                throwException(format(errorMessageTemplate,errorMessageArgs),showPage);
            }
        }
    }

    /**
     * 校验状态
     * @param expression
     */
    public static void checkState(boolean expression) {
        checkState(expression,"",false);
    }
    public static void checkState(boolean expression, Object errorMessage){
        checkState(expression,errorMessage,false);
    }
    public static void checkState(boolean expression, Object errorMessage,boolean showPage) {
        if (!expression)
                throwException(errorMessage,showPage);
    }
    public static void checkState(boolean expression, String errorMessageTemplate,boolean showPage, Object... errorMessageArgs) {
        if (!expression) {
            if(errorMessageArgs == null){
                throwException(errorMessageTemplate,showPage);
            }else{
                throwException(format(errorMessageTemplate,errorMessageArgs),showPage);
            }
        }
    }

    /**
     * 验证是否为空
     * @param reference
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference,"",false);
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        return checkNotNull(reference,errorMessage,false);
    }
    public static <T> T checkNotNull(T reference, Object errorMessage,boolean showPage) {
        if (reference == null) {
            throwNullPointerException(String.valueOf(errorMessage),showPage);
        }
        return reference;
    }
    public static <T> T checkNotNull(T reference,  String errorMessageTemplate,boolean showPage, Object... errorMessageArgs) {
        if (reference == null) {
            throwNullPointerException(format(errorMessageTemplate, errorMessageArgs),showPage);
        }
        return reference;
    }
    /**
     * 验证是字符串否为空或者空字符
     * @param reference
     * @return
     */
    public static String checkNotNullAndEmpty(String reference) {
        return checkNotNullAndEmpty(reference,"",false);
    }

    public static String checkNotNullAndEmpty(String reference, Object errorMessage) {
        return checkNotNullAndEmpty(reference,errorMessage,false);
    }
    public static String checkNotNullAndEmpty(String reference, Object errorMessage,boolean showPage) {
        if (reference == null || "".equals(reference)) {
            throwNullPointerException(String.valueOf(errorMessage),showPage);
        }
        return reference;
    }
    public static String checkNotNullAndEmpty(String reference,String errorMessageTemplate,boolean showPage, Object... errorMessageArgs) {
        if (reference == null || "".equals(reference)) {
            throwNullPointerException(format(errorMessageTemplate, errorMessageArgs),showPage);
        }
        return reference;
    }

    static String format(String template, Object... args) {
        template = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }
        return builder.toString();
    }
}
