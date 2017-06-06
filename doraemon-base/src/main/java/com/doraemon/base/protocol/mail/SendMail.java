package com.doraemon.base.protocol.mail;


import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Properties;

import com.doraemon.base.util.StringReplace;

/**
 * 发送邮件
 * Created by zbs on 2017/6/5.
 */
@Slf4j
@Configuration
public class SendMail {

    @Autowired
    MailConfig mailConfig;

    public SendMail setTo(String to) {
        mailConfig.setTo(to);
        return this;
    }

    public SendMail setForm(String form) {
        mailConfig.setFrom(form);
        return this;
    }

    public SendMail setCc(String cc) {
        mailConfig.setCc(cc);
        return this;
    }

    public SendMail setBcc(String bcc) {
        mailConfig.setBcc(bcc);
        return this;
    }

    public SendMail setSubject(String subject) {
        mailConfig.setSubject(subject);
        return this;
    }

    public SendMail setContext(String context) {
        mailConfig.setContext(context);
        return this;
    }

    public SendMail setValues(String[] values) {
        mailConfig.setValues(values);
        return this;
    }
    public void send() throws Exception {
        log.info("[邮件配置] : " + mailConfig.toString());
        //1. 参数校验
        checkParameter(mailConfig, mailConfig.getSubject());
        //2. 获取Session
        Session session = getSession(mailConfig);
        //3. 创建邮件
        MimeMessage mimeMessage = createMail(session, mailConfig, mailConfig.getSubject(), mailConfig.getContext(), mailConfig.getValues());
        //4. 发送邮件
        sendMail(session, mimeMessage, mailConfig);
        //5. 保存邮件
        saveMailToLocal(mimeMessage, mailConfig);
    }

    /**
     * 保存邮件到本地
     *
     * @param mimeMessage
     * @param mailConfig
     * @throws Exception
     */
    private static void saveMailToLocal(MimeMessage mimeMessage, MailConfig mailConfig) throws Exception {
        if (mailConfig.isNotSave())
            return;
        OutputStream out = null;
        try {
            String path = mailConfig.getSavePath() + String.valueOf(System.currentTimeMillis());
            log.info("保存邮件到本地路径 --> " + path);
            out = new FileOutputStream(path);
            mimeMessage.writeTo(out);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 发送邮件
     *
     * @param session
     * @param mimeMessage
     * @param mailConfig
     * @throws Exception
     */
    private static void sendMail(Session session, MimeMessage mimeMessage, MailConfig mailConfig) throws Exception {
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(mailConfig.getAccount(), mailConfig.getPasswd());
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }

    /**
     * 获取Session
     *
     * @param mailConfig
     * @return
     */
    private static Session getSession(MailConfig mailConfig) {
        Properties props = new Properties();
        String port;
        if (mailConfig.ssl) {
            port = mailConfig.getSmtpPort() == 0 ? "465" : String.valueOf(mailConfig.getSmtpPort());
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", port);
        } else {
            port = mailConfig.getSmtpPort() == 0 ? "25" : String.valueOf(mailConfig.getSmtpPort());
        }
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailConfig.getSmtpHost());
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false); // 设置为debug模式, 可以查看详细的发送 log
        return session;
    }


    /**
     * 创建邮件正文
     *
     * @param session
     * @param mailConfig
     * @param content
     * @param values
     * @return
     * @throws Exception
     */
    private static MimeMessage createMail(Session session, MailConfig mailConfig, String subject, String content, String... values) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailConfig.getFrom(), mailConfig.getFrom(), "UTF-8"));  //发件人
        for (String toName : mailConfig.getTo().split(",")) {
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toName, toName, "UTF-8")); //收件人
        }
        if (mailConfig.getCc() != null) {
            for (String ccName : mailConfig.getCc().split(","))
                message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccName, ccName, "UTF-8")); //抄送（可选）
        }
        if (mailConfig.getBcc() != null) {
            for (String bccName : mailConfig.getBcc().split(","))
                message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bccName, bccName, "UTF-8"));  //密送（可选）
        }
        message.setSubject(subject, "UTF-8"); //邮件主题
        message.setContent(StringReplace.findAndReplace(content, values), "text/html;charset=UTF-8");//邮件正文（可以使用html标签）
        //设置显示的发件时间
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }


    /**
     * 校验相关参数
     *
     * @param mailConfig
     */
    private void checkParameter(MailConfig mailConfig, String subject) {
        Preconditions.checkNotNull(mailConfig.getSmtpHost(), "SMTP 服务器地址不能为空.");
        Preconditions.checkNotNull(mailConfig.getAccount(), "邮件服务器账号不能为空.");
        Preconditions.checkNotNull(mailConfig.getPasswd(), "邮件服务器密码不能为空.");
        Preconditions.checkNotNull(mailConfig.getFrom(), "发件人不能为空.");
        Preconditions.checkNotNull(mailConfig.getTo(), "收人不能为空.");
        Preconditions.checkNotNull(subject, "主题不能为空.");
        if (!mailConfig.isNotSave()) {
            Preconditions.checkState(mailConfig.getSavePath() == null || "".equals(mailConfig.getSavePath()), "如果选择要本地保存邮件,保存路径不能为空.");
            File file = new File(mailConfig.getSavePath());
            Preconditions.checkState(file.exists() && file.isDirectory(), "文件目录不存在或者不是一个有效的文件路径");
        }
    }
}
