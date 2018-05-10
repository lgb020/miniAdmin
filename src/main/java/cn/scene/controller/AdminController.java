package cn.scene.controller;

import cn.scene.model.Music;
import cn.scene.model.SceneReport;
import cn.scene.model.Sysfile;
import cn.scene.service.FileService;
import cn.scene.service.MuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MuService muService;
    @Autowired
    private FileService fileService;

    //场景举报信息查询
    @RequestMapping("/scene")
    public @ResponseBody List<SceneReport> report(HttpServletRequest request){
        return null;
    }

    //删除场景，给原创作者发送警告消息
    @RequestMapping("/delete")
    public @ResponseBody int deleteScene(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regex = "^[0-9]+$";
        if(index.matches(regex)){

        }
        return 0;
    }


    //音乐素材管理，分页显示
    @RequestMapping("/musicList")
    public @ResponseBody List<Music> musicList(HttpServletRequest request){
      int page =Integer.parseInt( request.getParameter("page"));
      List<Music> list = muService.musicList(page);
      return list;
    }

    //音乐总页数
    @RequestMapping("/musicCount")
    public @ResponseBody int musicCount(HttpServletRequest request){
        return muService.allPage();
    }


    //音乐素材管理，删除
    @RequestMapping("/deleteMusic")
    public @ResponseBody int deleteMusic(HttpServletRequest request){
        int id =Integer.parseInt(request.getParameter("id"));
        int i = muService.deleteMusic(id);
        return i;
    }

    //图片素材管理，添加
    @RequestMapping("/addFile")
    public @ResponseBody int addFile(HttpServletRequest request){
        String image = request.getParameter("image");
        String type =request.getParameter("type");
        int i =fileService.addFile(type,image);
        return i ;

    }

    //图片素材管理，删除
    @RequestMapping("/deleteFile")
    public @ResponseBody int deleteFile(HttpServletRequest request){
        int id =Integer.parseInt(request.getParameter("id"));
        int i = fileService.deleteFile(id);
        return i;
    }

    //图片素材查询 0-背景 1-图片
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

    //图片类型查询总页数
    @RequestMapping("/fileCount")
    public @ResponseBody int fileCount(HttpServletRequest request){
        String type = request.getParameter("type");
        String regx = "^0|1$";
        int counts = 0;
        if(type.matches(regx)){
            counts = fileService.counts(type);
        }
        return counts;
    }

}
