package cn.scene.controller;

import cn.scene.model.Advertise;
import cn.scene.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 广告控制器
 */
@Controller
@RequestMapping("/adv")
public class AdvController {

    @Autowired
    private AdvService advService;

    //首页轮播图
    @RequestMapping("/index")
    public @ResponseBody List<Advertise> index(HttpServletRequest request){
        List<Advertise> list = advService.info();
        return list;
    }

    //查询所有轮播图
    @RequestMapping("/list")
    public  @ResponseBody List<Advertise> adList(HttpServletRequest request){
        List<Advertise> list = advService.adList();
        return list;
    }

    //添加轮播图
    @RequestMapping("/upload")
    public  @ResponseBody int addAd(HttpServletRequest request){
        String image = request.getParameter("image");
        int result = advService.insertImg(image);
        return result;
    }

    //删除轮播图
    @RequestMapping("/deleteAd")
    public  @ResponseBody int deleteAd(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int i = advService.deleteAd(id);
        return i ;
    }

}
