package com.doraemon.base.protocol.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件config bean
 * Created by zbs on 2017/6/5.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    String smtpHost = null;
    int smtpPort = 25;
    String account = null;
    String passwd = null;
    boolean ssl = false;
    String subject = null;
    String context = null;
    String[] values = null;
    String from = null;
    String to = null;
    String cc = null;
    String bcc = null;
    boolean notSave = true;
    String savePath = null;

}
