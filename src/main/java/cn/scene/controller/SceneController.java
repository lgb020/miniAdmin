package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.model.ScenePage;
import cn.scene.model.User;
import cn.scene.service.SceneService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //场景搜索
    @RequestMapping("/search")
    public @ResponseBody List<Scene> search(HttpServletRequest request){
        String content = request.getParameter("content");
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(content)){
            list = sceneService.search(content);
        }
        return list;
    }

    //获取新添加数据的id
    @RequestMapping("/issue/info")
    public @ResponseBody int getIssueId(HttpServletRequest request){
        String index = request.getParameter("fromScene");
        int fromScene = 0;  //父场景id，0为原创
        if(StringUtils.isNotBlank(index)){
            fromScene = Integer.parseInt(index);
        }
        Scene scene = new Scene();
        //初始化场景数据
        User user = (User)request.getSession().getAttribute("user");
        scene.setUserId(user.getId());
        scene.setFromScene(fromScene);
        sceneService.init(scene);
        int id = scene.getId();
        return id;
    }

    //保存场景页面数据
    @RequestMapping("/issue")
    public @ResponseBody int issue(HttpServletRequest request, ScenePage scenePage){
        if(scenePage!=null){
            sceneService.insert(scenePage);
            return 1;
        }
        return 0;
    }

    //更新封面图
    @RequestMapping("/cover")
    public @ResponseBody int uodateCover(HttpServletRequest request){
        String cover = request.getParameter("cover");
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(StringUtils.isNotBlank(cover) && index.matches(regx)){
            int id = Integer.parseInt(index);
            result = sceneService.updateCover(cover,id);
        }
        return result;
    }

    //场景信息查询
    @RequestMapping("/scene")
    public @ResponseBody Scene scebeInfo(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regx="^[0-9]+$";
        Scene scene = new Scene();
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index);
            scene = sceneService.scene(sceneId);
        }
        return scene;
    }

    //场景页面数据查询
    @RequestMapping("/list")
    public @ResponseBody List<ScenePage> Pageinfo(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regx="^[0-9]+$";
        List<ScenePage> list = new ArrayList<>();
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index);
            list = sceneService.pageInfo(sceneId);
        }
        return list;
    }

    //查询共享场景
    @RequestMapping("/view")
    public String sceneView(HttpServletRequest request,Model model){
        String code = request.getParameter("code");
        Scene scene = new Scene();
        if(StringUtils.isNotBlank(code)){
            scene = sceneService.scene(code);
            model.addAttribute("scene",scene);
            return "view";
        }
        return null;
    }

    //场景兑换
    @RequestMapping("/exchange")
    public @ResponseBody int exchange(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index);
            //获取登录用户信息
            User user = (User)request.getSession().getAttribute("user");
            int result = sceneService.exchangeScene(user,sceneId);
            return result;
        }
        return 0;
    }

}