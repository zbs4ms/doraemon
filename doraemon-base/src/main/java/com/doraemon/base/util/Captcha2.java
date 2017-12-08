package com.doraemon.base.util;

import com.doraemon.base.util.bean.CaptchaData;
import com.github.bingoohuang.patchca.background.MyCustomBackgroundFactory;
import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import java.awt.*;
import java.util.Random;

/**
 * Created by zbs on 2017/6/27.
 */

public class Captcha2 {

    private ConfigurableCaptchaService cs;
    private static Random random = new Random();
    private Captcha2(){}

    public static Captcha2 create(){
        Captcha2 captcha2 =  new Captcha2();
        captcha2.cs = new ConfigurableCaptchaService();
        captcha2.cs.setColorFactory(new SingleColorFactory(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
        return captcha2;
    }

    /**
     * 验证码的长度
     * @param maxLength 最长
     * @param minLength 最短
     * @return
     */
    public Captcha2 setCodeLength(int maxLength, int minLength){
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("ABCDEFGHIJKMNPSTUVWXYZ23456789");
        wordFactory.setMaxLength(maxLength);
        wordFactory.setMinLength(minLength);
        cs.setWordFactory(wordFactory);
        return this;
    }

    /**
     * 字体颜色
     * @param color
     * @return
     */
    public Captcha2 setColor(Color color){
        cs.setColorFactory(new SingleColorFactory(color));
        return this;
    }

    /**
     * 字体大小
     * @param max
     * @param size
     * @return
     */
    public Captcha2 setSize(int max, int size){
        RandomFontFactory font = new RandomFontFactory();
        font.setMaxSize(max);
        font.setMinSize(size);
        cs.setFontFactory(font);
        return this;
    }


    /**
     * 背景色
     * @param isLines 是否加入干扰线,干扰线背景为白色
     * @return
     */
    public Captcha2 setBackground(boolean isLines){
        if(isLines) {
            MyCustomBackgroundFactory background = new MyCustomBackgroundFactory();
            cs.setBackgroundFactory(background);
        }else {
            cs.setBackgroundFactory(new SingleColorBackgroundFactory(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
        }
        return this;
    }

    public CaptchaData build(){
        setFilterFactory();
        CaptchaData captchaData = new CaptchaData();
        captchaData.setCode(cs.getCaptcha().getWord());
        captchaData.setImage(cs.getCaptcha().getImage());
        return captchaData;
    }

    public CaptchaData build(boolean isLines, int codeMax, int codeMin, int wordMaxSize, int wordMinSize){
        CaptchaData captchaData = Captcha2.create()
                .setBackground(isLines)
                .setCodeLength(codeMax, codeMin)
                .setSize(wordMaxSize, wordMinSize)
                .build();
        return captchaData;
    }

    /**
     * 随机曲线样式
     */
    private void setFilterFactory(){
        Random r = new Random();
        CurvesRippleFilterFactory crff = new CurvesRippleFilterFactory(cs.getColorFactory());
        MarbleRippleFilterFactory mrff = new MarbleRippleFilterFactory();
        DoubleRippleFilterFactory drff =  new DoubleRippleFilterFactory();
        WobbleRippleFilterFactory wrff = new WobbleRippleFilterFactory();
        DiffuseRippleFilterFactory dirff = new DiffuseRippleFilterFactory();
        switch (r.nextInt(5)) {
            case 0:
                cs.setFilterFactory(crff);
                break;
            case 1:
                cs.setFilterFactory(mrff);
                break;
            case 2:
                cs.setFilterFactory(drff);
                break;
            case 3:
                cs.setFilterFactory(wrff);
                break;
            case 4:
                cs.setFilterFactory(dirff);
                break;
        }
    }


}
