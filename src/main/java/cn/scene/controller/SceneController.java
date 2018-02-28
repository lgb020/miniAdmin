package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.model.ScenePage;
import cn.scene.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 场景控制器
 */
@Controller
@RequestMapping("/s")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    //推荐-精选模板
    @RequestMapping("/info")
    public @ResponseBody List<Scene> info(HttpServletRequest request){
        String page = request.getParameter("page");
        String regx = "^[0-9]+";
        List<Scene> list = new ArrayList<>();
        if(page.matches(regx)){
            int temp = Integer.parseInt(page);
            list = sceneService.sceneInfo(temp);
        }
        return list;
    }

    //推荐-精选模板总页数
    @RequestMapping("/i/count")
    public @ResponseBody int iCount(HttpServletRequest request){
        return sceneService.count();
    }

    //推荐-最新推荐
    @RequestMapping("/news")
    public @ResponseBody List<Scene> news(HttpServletRequest request){
        List<Scene> list = sceneService.selectNews();
        return list;
    }

    //热门模板-最新模板
    @RequestMapping("/hot")
    public @ResponseBody List<Scene> hot(HttpServletRequest request){
        String page = request.getParameter("page");
        String regx = "^[0-9]+";
        List<Scene> list = new ArrayList<>();
        //匹配是否为0和1，0为热门模板，1为换一批
        if(page.matches(regx)){
            int temp = Integer.parseInt(page);
            list = sceneService.hotInfo(temp);
        }
        return list;
    }

    //热门模板-最新模板总数
    @RequestMapping("/h/count")
    public @ResponseBody int hCount(HttpServletRequest request){
        return sceneService.hCount();
    }

    //热门模板-热门推荐
    @RequestMapping("/hot/recommend")
    public @ResponseBody List<Scene> hotRecommend(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page = Integer.parseInt(index);
            list = sceneService.hotPage(page);
        }
        return list;
    }

    //热门模板-热门推荐总页数
    @RequestMapping("/hot/count")
    public @ResponseBody int hotCount(HttpServletRequest request){
        return sceneService.hotPageCount();
    }

    //企业宣传-热销
    @RequestMapping("/company")
    public @ResponseBody List<Scene> companyList(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page =Integer.parseInt(index);
            list = sceneService.companyScene(page);
        }
        return list;
    }

    //企业宣传-热销总数
    @RequestMapping("/company/count")
    public @ResponseBody int companyCount(HttpServletRequest request){
        return sceneService.companySceneCount();
    }

    //个人相册
    @RequestMapping("/photo")
    public @ResponseBody List<Scene> selfPhoto(HttpServletRequest request){
        String index = request.getParameter("page");
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page =Integer.parseInt(index);
            list = sceneService.photoScene(page);
        }
        return list;
    }

    //个人相册总数
    @RequestMapping("/photo/count")
    public @ResponseBody int selfPhotoCount(HttpServletRequest request){
        return sceneService.photoSceneCount();
    }

    //分类查询
    @RequestMapping("/part")
    public @ResponseBody List<Scene> partScene(HttpServletRequest request){
        String temp = request.getParameter("type");
        String charge = request.getParameter("charge"); //1-积分兑换,0-免积分
        String regx = "^[0-9]$";
        String cRegx = "^0|1$";
        List<Scene> list = new ArrayList<>();
        if(temp.matches(regx) &&charge.matches(cRegx)){
            int type = Integer.parseInt(temp);
            int isCharge = Integer.parseInt(charge);
            list = sceneService.TypeScene(type,isCharge);
        }
        return list;
    }

    //获取新添加数据的id
    @RequestMapping("/issue/id")
    public int getIssueId(HttpServletRequest request,Scene scene){
        sceneService.init(scene);
        int id = scene.getId();
        return id;
    }

    //场景发布
    @RequestMapping("/issue")
    public void issue(HttpServletRequest request, ScenePage scenePage){
        if(scenePage!=null){
            sceneService.insert(scenePage);
        }
    }

}
