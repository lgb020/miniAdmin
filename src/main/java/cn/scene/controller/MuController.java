package cn.scene.controller;

import cn.scene.model.Music;
import cn.scene.service.MuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 音乐控制器
 */
@Controller
@RequestMapping("/music")
public class MuController {

    @Autowired
    private MuService muService;

    //音乐列表
    @RequestMapping("/info")
    public @ResponseBody List<Music> info(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Music> list = new ArrayList<>();
        if(index.matches(regx)){
            int page = Integer.parseInt(index);
            list = muService.list(page);
        }
        return list;
    }

    //音乐查找
    @RequestMapping("/search")
    public @ResponseBody List<Music> search(HttpServletRequest request){
        String content = request.getParameter("content");
        List<Music> list = new ArrayList<>();
        if(StringUtils.isNotBlank(content)){
            list = muService.search(content);
        }
        return list;
    }

    //音乐总页数
    @RequestMapping("/count")
    public @ResponseBody int count(HttpServletRequest request){
        return muService.allPage();
    }

    //更新音乐文件
    @RequestMapping("/update")
    public @ResponseBody int update(HttpServletRequest request){
        String music = request.getParameter("music");
        String mTitle = request.getParameter("mTitle");
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(StringUtils.isNotBlank(music) && StringUtils.isNotBlank(mTitle) && index.matches(regx)){
            int id = Integer.parseInt(index);
            result = muService.updateMusic(id,music,mTitle);
        }
        return result;
    }

    //音乐上传
    @RequestMapping("/upload")
    public @ResponseBody int upload(HttpServletRequest request){
        String mTitle = request.getParameter("mTitle");
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(StringUtils.isNotBlank(mTitle) && index.matches(regx)){
            int id = Integer.parseInt(index);
            try {
                result = muService.uploadMusic(id,mTitle,request);
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }
        return result;
    }

}
