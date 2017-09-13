package com.doraemon.base.controller.listener;

import com.doraemon.base.controller.bean.LoginMessage;
import com.doraemon.base.util.VerifyParameter;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 监听session的创建和session的销毁,记录登陆
 * Created by zbs on 2017/9/12.
 */
@WebListener
public abstract class SessionListener implements HttpSessionListener {

    static ConcurrentMap<String, LoginMessage> map = new ConcurrentHashMap<>();

    abstract String getUserSessionKey();

    abstract void destroyedHandle();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext ctx = httpSessionEvent.getSession().getServletContext();
        String userId = (String) ctx.getAttribute(getUserSessionKey());
        if (VerifyParameter.notNullAndEmpty(userId) && map.get(userId) == null) {
            LoginMessage loginMessage = new LoginMessage();
            loginMessage.setUserId(userId);
            loginMessage.setStartTime(new Date());
            map.put(userId, loginMessage);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext ctx = httpSessionEvent.getSession().getServletContext();
        String userId = (String) ctx.getAttribute(getUserSessionKey());
        if (VerifyParameter.notNullAndEmpty(userId) && map.get(userId) != null) {
                LoginMessage loginMessage = map.get(userId);
                loginMessage.setStopTime(new Date());
                destroyedHandle();
        }
    }
}
