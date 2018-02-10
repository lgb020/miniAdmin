package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/info")
    public @ResponseBody List<Scene> info(HttpServletRequest request){
        String index = request.getParameter("index");
        List<Scene> list = sceneService.sceneInfo();
        return list;
    }


}
