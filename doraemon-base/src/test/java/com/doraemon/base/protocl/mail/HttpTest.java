package com.doraemon.base.protocl.mail;

import com.doraemon.base.protocol.http.HttpAgent;
import org.junit.Test;

/**
 * Created by zbs on 2017/12/18.
 */
public class HttpTest {

    String aaa = "http://dataapi.bbdservice.com/api/bbd_baidu_news/";
    String bbb = "company=中铝国际&appkey=18hmah3ar3qjr3dvv2b6xmzxampiynzc";

    @Test
    public void sendHttp() throws Exception {
        HttpAgent.create().sendGet(aaa,bbb);
    }
}
