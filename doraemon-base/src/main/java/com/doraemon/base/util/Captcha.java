package com.doraemon.base.util;

import com.doraemon.base.util.bean.CaptchaData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码图片
 * Created by zbs on 2017/6/15.
 */
public class Captcha {

    public static Captcha create(){
        return new Captcha();
    }

    /**
     * 生成验证码图片
     * @param width 长度
     * @param height 高度
     * @param fontSize 字体大小
     * @param color 背景颜色
     * @param fontColor 字体和噪点颜色
     * @param length 验证码字符个数
     * @return
     * @throws Exception
     */
    public CaptchaData getImage(int width, int height, int fontSize, Color color, Color fontColor, int length) throws Exception {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();
        //颜色
        graphics.setColor(color);
        graphics.fillRect(0,0,width,height);
        //查找系统中的字体
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();
        //随机字体
        graphics.setColor(fontColor);
        graphics.setFont(new Font(fontName[random.nextInt(fontName.length-1)],Font.BOLD,fontSize));
        //构造字符
        String validateCode = RandomUtil.getRandomLetterAndNum(length);
        int space = width/(length+1);
        int count = 0;
        int strHeight = height/2+graphics.getFontMetrics().getHeight()/3;
        double rate=1.2;
        int strWidth = (int)(width/2-rate*graphics.getFontMetrics().stringWidth(validateCode)/2);


        FontMetrics fm = graphics.getFontMetrics();
        int stringWidth = fm.stringWidth(validateCode);
        int stringAscent = fm.getAscent();
        int xCoordinate = width/2 - stringWidth/2;
        int yCoordinate = height/2 +stringAscent/2;

        for(int i=1;i<length+1;i++){
            //graphics.drawString(validateCode.substring(count,count+1),space * i,strHeight);
            graphics.drawString(validateCode.substring(count,count+1),strWidth,yCoordinate);
            int orgStringWight=graphics.getFontMetrics().stringWidth(validateCode);
            int orgStringLength = validateCode.length();
            strWidth=(int)(strWidth+(double)orgStringWight/(double)orgStringLength*rate);
            count++;
        }
        //加入噪点
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(space);
            int yl = random.nextInt(space);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        graphics.dispose();
        CaptchaData verificationCode = new CaptchaData();
        verificationCode.setCode(validateCode);
        verificationCode.setImage(image);
        return verificationCode;
    }

}
