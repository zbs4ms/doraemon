package com.doraemon.base.protocl.mail;

import com.doraemon.base.Main;
import com.doraemon.base.protocol.mail.SendMail;
import com.doraemon.base.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zbs on 2017/6/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class SendMailTest {

    @Autowired
    SendMail sendMail;

    @Test
    public void sendMail() throws Exception {
        sendMail.setTo("zhoubinshan@163.com,zbs4ms@163.com")
                .setCc("zbs4ms@163.com,zhoubinshan@bbdservice.com")
                .setBcc("zhoubinshan@bbdservice.com,jishitech666@163.com")
                .setForm("jishitech666@163.com")
                .setSubject(RandomUtil.getRandomGBK(5))
                .setContext(" {0} "+RandomUtil.getRandomGBK(3)+" {1} "+RandomUtil.getRandomGBK(4))
                .setValues(new String[]{RandomUtil.getRandomGBK(2),RandomUtil.getRandomGBK(4)})
                .send();
    }
}
