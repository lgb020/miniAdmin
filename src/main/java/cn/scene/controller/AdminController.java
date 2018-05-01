package cn.scene.controller;

import cn.scene.model.SceneReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台管理控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

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

}
