package com.doraemon.base.protocol.http;

import com.doraemon.base.util.VerifyParameter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zbs on 16/7/20.
 */
@Slf4j
public class HttpAgent {

    private HttpAgent() {
    }

    private String authorization = null;

    public static HttpAgent create() {
        HttpAgent httpAgent = new HttpAgent();
        return httpAgent;
    }

    public HttpAgent setAuthorization(String value) {
        this.authorization = value;
        return this;
    }

    private URLConnection initPostConfig(String url) throws IOException {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("Authorization", authorization);
        //超时为1秒
        conn.setConnectTimeout(1000);
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        return conn;
    }

    private URLConnection initGetConfig(String url) throws IOException {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
//        // 设置通用的请求属性
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent",
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//        conn.setRequestProperty("Authorization",authorization);
//
//        String cookieVal =conn.getHeaderField("Set-Cookie");    //获取session
//        String JSESSIONID = (cookieVal.substring(0,cookieVal.indexOf(";")));
//        conn.setRequestProperty("Cookie", JSESSIONID);//设置session

        return conn;
    }

    public String sendPost(String url, String param) throws Exception {
        URLConnection conn = initPostConfig(url);
        if (conn == null)
            throw new Exception("HTTP配置错误，未能成功建立HTTP连接。");
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } finally {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        }
        return decode(result);
    }

    public String sendGet(String url, Map<String, String> map) throws Exception {
        if (map != null) {
            StringBuffer sb = new StringBuffer("?");
            for (Map.Entry<String, String> set : map.entrySet())
                //对传入的参数进行编码,避免nginx传递参数乱码
                sb.append(set.getKey() + "=" + URLEncoder.encode(set.getValue(),"utf-8") + "&");
            return sendGet(url + sb.toString());
        }
        return sendGet(url);
    }

    public String sendGet(String url, String param) throws Exception {
        //如果参数为空,直接发送url
        if (!VerifyParameter.notNullAndEmpty(param))
            return sendGet(url);
        //如果参数为空,或者不正确的值,直接发送url
        String[] stringArray = param.split("&");
        if (stringArray == null || stringArray.length < 1)
            return sendGet(url);
        StringBuffer sb = new StringBuffer();
        for (String s : stringArray) {
            String[] v = s.split("=");
            //如果 ** = ** 为不正确的格式的话,丢弃本参数
            if (v == null || v.length != 2) {
                log.warn("错误的参数格式,会在请求时被抛弃:" + s);
                continue;
            }
            //对传入的参数进行编码,避免nginx传递参数乱码
            sb.append(v[0] + "=" + URLEncoder.encode(v[1],"utf-8") + "&");
        }
        return sendGet(url + "?" + sb.toString());
    }


    public String sendGet(String url) throws Exception {
        log.info("发送的请求HTTP为:" + url);
        URLConnection conn = initGetConfig(url);
        if (conn == null)
            throw new Exception("HTTP配置错误，未能成功建立HTTP连接。");
        String result = "";
        BufferedReader in = null;
        try {
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new Exception("发送 GET 请求出现异常！" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return decode(result);
    }


    private URLConnection initGetConfigNoHeader(String url) throws IOException {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        return conn;
    }

    public String sendGetNoHeader(String url,Map<String,String> map) throws Exception {
        if(map!=null){
            StringBuffer sb = new StringBuffer();
            sb.append(url+"?");
            for(Map.Entry<String,String> set: map.entrySet()){
                sb.append(set.getKey()+"="+ URLEncoder.encode(set.getValue(),"utf-8") +"&");
            }
            sb.deleteCharAt(sb.length()-1);
            url+="?";
            url +=sb.toString();
        }
        URLConnection conn = initGetConfigNoHeader(url);
        return getResult(conn);

    }

    private String  getResult(URLConnection conn) throws Exception {
        if (conn == null)
            throw new Exception("HTTP配置错误，未能成功建立HTTP连接。");
        String result = "";
        BufferedReader in = null;
        try {
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new Exception("发送 GET 请求出现异常！" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return decode(result);
    }

    private String decode(String result) throws UnsupportedEncodingException {
        return decodeUnicode(result);
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);

                }

            } else

                outBuffer.append(aChar);

        }

        return outBuffer.toString();

    }
}
