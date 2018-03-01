package cn.scene.controller;

import cn.scene.model.Sysfile;
import cn.scene.model.Upfile;
import cn.scene.model.User;
import cn.scene.service.FileService;
import cn.scene.util.ImgEcoding;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 场景素材表
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    //系统素材上传
    @RequestMapping("/bg")
    public @ResponseBody int imgUpload(HttpServletRequest request){
        String url = request.getParameter("url");
        String type = request.getParameter("type");
        String regx = "^0|1$";
        int result = 0;
        if(StringUtils.isNotBlank(url) && type.matches(regx)){
            result = fileService.upload(type,url);
        }
        return result;
    }

    //背景图查询
    @RequestMapping("/info")
    public @ResponseBody List<Sysfile> info(HttpServletRequest request){
        String index = request.getParameter("page");
        String type = request.getParameter("type");
        String regx = "^[0-9]+$";
        String tRegx = "^0|1$";
        List<Sysfile> info = new ArrayList<>();
        if(index.matches(regx) && type.matches(tRegx)){
            int page = Integer.parseInt(index);
            info = fileService.info(type,page);
        }
        return info;
    }

    //素材记录
    @RequestMapping("/record")
    public @ResponseBody List<Upfile> record(HttpServletRequest request){
        String index = request.getParameter("page");
        String type = request.getParameter("type");
        String regx = "^[0-9]+$";
        String tRegx = "^0|1$";
        List<Upfile> info = new ArrayList<>();
        if(index.matches(regx) && type.matches(tRegx)){
            int page = Integer.parseInt(index);
            User user = (User)request.getSession().getAttribute("user");
            int userId = user.getId();
            info = fileService.recordInfo(type,page,userId);
        }
        return info;
    }

    //素材上传
    @RequestMapping("/upload")
    public @ResponseBody int upload(HttpServletRequest request) throws Exception{
        String img = request.getParameter("img");
        String type = request.getParameter("type");
        String url = ImgEcoding.GenerateImage(img,request);
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();
        int result = fileService.addInfo(userId,type,url);
        return result;
    }
}
