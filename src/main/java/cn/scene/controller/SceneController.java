package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.service.SceneService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 场景控制器
 */
@Controller
@RequestMapping("/scene")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    //精选模板
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public @ResponseBody List<Scene> info(HttpServletRequest request){
        String index = request.getParameter("index");
        String regx = "^1|0$";
        List<Scene> list = new ArrayList<>();
        //匹配是否为0和1，0为初始化显示，1为换一批
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int temp = Integer.parseInt(index);
            list = sceneService.sceneInfo(temp);
        }
        return list;
    }

    //最新推荐
    @RequestMapping(value = "/news",method = RequestMethod.POST)
    public @ResponseBody List<Scene> news(HttpServletRequest request){
        List<Scene> list = sceneService.selectNews();
        return list;
    }

    //热门模板,最新模板
    @RequestMapping(value = "/hot",method = RequestMethod.POST)
    public @ResponseBody List<Scene> hot(HttpServletRequest request){
        String index = request.getParameter("index");
        String regx = "^1|0$";
        List<Scene> list = new ArrayList<>();
        //匹配是否为0和1，0为热门模板，1为换一批
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int temp = Integer.parseInt(index);
            list = sceneService.hotInfo(temp);
        }
        return list;
    }

    //热门推荐
    @RequestMapping(value = "/hot/recommend",method = RequestMethod.POST)
    public @ResponseBody List<Scene> hotRecom(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int page = Integer.parseInt(index);
            list = sceneService.hotPage(page);
        }
        return list;
    }

    //企业宣传,热销
    @RequestMapping(value = "/company",method = RequestMethod.POST)
    public @ResponseBody List<Scene> companyList(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int page =Integer.parseInt(index);
            list = sceneService.companyScene(page);
        }
        return list;
    }

    //个人相册
    @RequestMapping(value = "/photo",method = RequestMethod.POST)
    public @ResponseBody List<Scene> selfPhoto(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int page =Integer.parseInt(index);
            list = sceneService.photoScene(page);
        }
        return list;
    }

    //分类查询
    @RequestMapping(value = "/part",method = RequestMethod.POST)
    public @ResponseBody List<Scene> partScene(HttpServletRequest request){
        String temp = request.getParameter("type");
        String charge = request.getParameter("charge"); //1-积分兑换,0-免积分
        String regx = "^[0-9]$";
        String cRegx = "^0|1$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(temp) && StringUtils.isNotBlank(charge) && temp.matches(regx) &&charge.matches(cRegx)){
            int type = Integer.parseInt(temp);
            int isCharge = Integer.parseInt(charge);
            list = sceneService.TypeScene(type,isCharge);
        }
        return list;
    }
}
