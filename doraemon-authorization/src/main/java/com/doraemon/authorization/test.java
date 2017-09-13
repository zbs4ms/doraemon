package com.doraemon.authorization;

import com.alibaba.fastjson.JSON;
import com.doraemon.base.util.VerificationImage;
import com.sun.tools.javac.util.List;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zbs on 2017/6/14.
 */
@RestController
@RequestMapping("/test")
public class test {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject changeState() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aa", "11");
        map.put("bb", "22");
        return JSON.parseObject(JSON.toJSONString(map));
    }

    @RequestMapping(value = "images", method = RequestMethod.GET)
    @ResponseBody
    public void images(HttpServletRequest request, HttpServletResponse response) throws Exception {


//        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, 80, 20);
//
//        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String[] fontName = e.getAvailableFontFamilyNames();
//        g.setFont(new Font(fontName[r.nextInt(fontName.length-1)],Font.PLAIN,18));
//        g.setColor(Color.black);
//        String str = validateCode.substring(0, 1);
//        g.drawString(str, 8, 15);
//        str = validateCode.substring(1, 2);
//        g.drawString(str, 28, 15);
//        str = validateCode.substring(2, 3);
//        g.drawString(str, 46, 15);
//        str = validateCode.substring(3, 4);
//        g.drawString(str, 64, 15);
//        for (int i = 0; i < 20; i++) {
//            int x = r.nextInt(80);
//            int y = r.nextInt(20);
//            int xl = r.nextInt(12);
//            int yl = r.nextInt(12);
//            g.drawLine(x, y, x + xl, y + yl);
//        }
//        g.dispose();

        VerificationImage.VerificationCode verificationCode = VerificationImage.create().getImage(100,25,Color.white,Color.black,4);
        HttpSession session = request.getSession(true);
        session.removeAttribute("validate");
        System.out.println("生成的验证码=" + verificationCode.getCode());
        session.setAttribute("validate", verificationCode.getCode());
        ImageIO.write(verificationCode.getImage(), "JPEG", response.getOutputStream());
    }
}
