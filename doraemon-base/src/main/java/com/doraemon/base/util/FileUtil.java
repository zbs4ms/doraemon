package com.doraemon.base.util;

import lombok.extern.log4j.Log4j;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;

/**
 * Created by zbs on 2017/6/15.
 */
@Log4j
public class FileUtil {

    /**
     * 保存文件到路径下
     * @param path 文件路径
     * @param name 文件名称
     * @param fileSuffixes 文件后缀
     * @param content 文件内存(支持 RenderedImage)
     */
    public static void saveFile(String path,String name,String fileSuffixes,Object content) throws Exception {
        File file =new File(path);
        if(file.exists()){
            log.info("文件路径"+path+"已经存在.不需要创建!");
        }else{
            log.info("创建文件路径"+path);
            file.mkdirs();
        }

        if (content instanceof RenderedImage) {
            log.info("保存图片文件"+path+"/"+name+"."+fileSuffixes);
            ImageIO.write((RenderedImage) content, fileSuffixes, new File(path + "/" + name + "." + fileSuffixes));
        }
    }
}
