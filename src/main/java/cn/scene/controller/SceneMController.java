package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.service.SceneMService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 场景管理:上架，编辑，设置，删除
 */
@Controller
@RequestMapping("/s/manage")
public class SceneMController {

    @Autowired
    private SceneMService sceneMService;

    //上架
    @RequestMapping("/shelve")
    public @ResponseBody Boolean shelve(HttpServletRequest request){
        String temp = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int sceneId = Integer.parseInt(temp);
            result = sceneMService.shelve(sceneId);
        }
        return result;
    }

    //设置
    @RequestMapping("/setting")
    public @ResponseBody Scene setting(HttpServletRequest request){
        String temp = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int sceneId = Integer.parseInt(temp);
            Scene scene = sceneMService.setting(sceneId);
            return scene;
        }
        return null;
    }

    //完成设置
    @RequestMapping("/issue")
    public @ResponseBody Boolean issue(HttpServletRequest request,Scene scene){
        if(scene!=null){
            int temp = sceneMService.issue(scene);
            if(temp>0){
                return true;
            }
        }
        return false;
    }

    //模板删除
    @RequestMapping("del")
    public @ResponseBody Boolean delete(HttpServletRequest request){
        String temp = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int sceneId = Integer.parseInt(temp);
            result = sceneMService.delete(sceneId);
            return result;
        }
        return result;
    }
}
