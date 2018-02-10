package cn.scene.controller;

import cn.scene.model.Advertise;
import cn.scene.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public @ResponseBody List<Advertise> index(HttpServletRequest request){
        List<Advertise> list = advService.info();
        return list;
    }
}
