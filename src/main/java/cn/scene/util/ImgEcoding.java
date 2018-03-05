package cn.scene.util;

import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 图片上传转码
 */
public class ImgEcoding {

    // base64字符串转化成图片,返回文件名
    public static String GenerateImage(String imgStr,String path) throws Exception{
        if (imgStr != null){
            BASE64Decoder decoder = new BASE64Decoder();
            //解码
            byte[] b = decoder.decodeBuffer(imgStr);
            //处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            //重命名
            String name = UUID.randomUUID().toString().replace("-","")+".png";
            String realPath = path+"/"+name;
            OutputStream out = new FileOutputStream(realPath);
            out.write(b);
            out.flush();
            out.close();
            return name;
        }
        return null;
    }
}
