package com.doraemon.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.doraemon.base.exceptions.PermissionExceptions;
import com.doraemon.base.exceptions.ShowExceptions;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by zbs on 16/7/1.
 */
public abstract class BaseController {

    /**
     * 获取用户ID
     * @return
     */
    public abstract Long getCurrentUserId();

    /**
     * 获取用户的token
     * @return
     */
    public abstract String getToken();


    /**
     * 取得Json结果包装器(带日志记录的),用于构json格式的统一消息返回包装.
     *
     * @return
     */
    protected ControllerResult ResponseWrapper() {
        return new ControllerResult(getCurrentRequest(), this.getClass());
    }


    /**
     * 统一的异常处理机制
     * @param e
     * @return
     */
    @ExceptionHandler
    public @ResponseBody JSONObject exceptionHandle(Exception e) {
        if (e == null) {
            return ResponseWrapper().addMessage("系统异常!").ExeFaild(CodeEnum.FAILURE_UNKNOWN_ERROR.getCode());
        } else if (e instanceof ShowExceptions){
           // ShowExceptions se = (ShowExceptions) e;
            e.printStackTrace();
            Result result = ResponseWrapper().addError(e).addMessage(e.getMessage());
            //return (se.getCode()!=null ? result.setErrorMark(se.getCode()) : result).ExeFaild(CodeEnum.FAILURE_SHOW_MSG.getCode());
            return result.ExeFaild(CodeEnum.FAILURE_SHOW_MSG.getCode());
        } else if (e instanceof PermissionExceptions) {
            e.printStackTrace();
            return ResponseWrapper().addError(e).addMessage(e.getMessage()).ExeFaild(CodeEnum.FAILURE_NO_PERMISSIONS.getCode());
        }
        e.printStackTrace();
        return ResponseWrapper().addError(e).addMessage(e.getMessage()).ExeFaild(CodeEnum.FAILURE_ERROR.getCode());
    }

    protected HttpServletRequest getCurrentRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 返回当前域名
     * @return
     */
    protected String getServerName(){
        HttpServletRequest request = getCurrentRequest();
        return request.getScheme()+"://"+request.getServerName();
    }

    /**
     * 返回全路径名
     * @return
     */
    protected String getServicePoth(){
        HttpServletRequest request = getCurrentRequest();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    }
}
