package cn.scene.util;

import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 图片上传转码
 */
public class ImgEcoding {

    // base64字符串转化成图片
    public static String GenerateImage(String imgStr, HttpServletRequest request) throws Exception{
        if (imgStr != null){
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgName = UUID.randomUUID().toString().replace("-", "");
            String imgFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/img/" +imgName+".jpg";
            OutputStream out = new FileOutputStream(imgFilePath);
            String url ="/upload//"+imgName+".jpg";
            out.write(b);
            out.flush();
            out.close();
            return url;
        }else{
            return null;
        }
    }
}
