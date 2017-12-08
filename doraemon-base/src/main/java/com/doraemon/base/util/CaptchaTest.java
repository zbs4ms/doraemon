package com.doraemon.base.util;

import com.doraemon.base.util.bean.CaptchaData;
import org.junit.Test;

import java.awt.*;

/**
 * Created by zbs on 2017/6/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class)
public class CaptchaTest {

    @Test
    public void getImage() throws Exception {
        CaptchaData captchaData = Captcha.create().getImage(1000, 250, 200, Color.white, Color.black, 4);
        System.out.println("[code] = " + captchaData.getCode());
        FileUtil.saveFile(System.getProperty("user.dir"), "captcha", "jpg", captchaData.getImage());
        for(int i=0;i<10;i++) {
            CaptchaData captchaData2 = Captcha2.create().build(true, 4, 4, 50, 50);
            FileUtil.saveFile(System.getProperty("user.dir"), "captcha2"+i, "png", captchaData2.getImage());
        }

    }
}
