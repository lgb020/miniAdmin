package cn.scene.controller;

import cn.scene.model.Scene;
import cn.scene.model.User;
import cn.scene.service.SceneMService;
import cn.scene.service.SceneService;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 场景管理:上架，编辑，设置，删除
 */
@Controller
@RequestMapping("/s/manage")
public class SceneMController {

    @Autowired
    private SceneMService sceneMService;
    @Autowired
    private SceneService sceneService;

    //上架
    @RequestMapping("/shelve")
    public @ResponseBody Boolean shelve(HttpServletRequest request){
        String index = request.getParameter("id");
        String jInden = request.getParameter("jifen");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(index.matches(regx) && jInden.matches(regx)){
            int id = Integer.parseInt(index);
            int jifen = Integer.parseInt(jInden);
            result = sceneMService.shelve(id,jifen);
        }
        return result;
    }

    //设置
    @RequestMapping("/setting")
    public @ResponseBody Scene setting(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            Scene scene = sceneMService.setting(id);
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
    @RequestMapping("/del")
    public @ResponseBody Boolean delete(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            result = sceneMService.delete(id);
        }
        return result;
    }

    //场景下架
    @RequestMapping("/down")
    public @ResponseBody int downScene(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            result = sceneMService.updateIssue(id);
        }
        return result;
    }

    //场景举报ip查询
    @RequestMapping("/ip")
    public @ResponseBody Boolean ipSelect(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String userIp = request.getParameter("ip"); //用户客户端ip
        String regx = "^[0-9]+$";
        String ipRegx = "^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$";
        if(index.matches(regx) && userIp.matches(ipRegx)){
            int sceneId = Integer.parseInt(index);
            return sceneMService.ipIsExit(sceneId,userIp);
        }
        return false;
    }

    //场景举报
    @RequestMapping("/report")
    public @ResponseBody int reportScene(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String userIp = request.getParameter("ip"); //用户客户端ip
        String content = request.getParameter("content");
        String regx = "^[0-9]+$";
        String ipRegx = "^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$";
        //正则表达式验证信息
        if(index.matches(regx) && userIp.matches(ipRegx)
                && StringUtils.isNotBlank(content)){
            int sceneId = Integer.parseInt(index);
            return sceneMService.reportScene(sceneId,userIp,content);
        }
        return 0;
    }

    //场景兑换
    @RequestMapping("/exchange")
    @Transactional
    public @ResponseBody int exchangeScene(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index);
            //查询场景信息
            Scene scene = sceneService.scene(sceneId);
            //更新场景信息为作者信息
            User user = (User)request.getSession().getAttribute("user");
            String code = UUID.randomUUID().toString().substring(0,8); //生成访问码
            scene.setFromScene(scene.getUserId()); //更新父场景
            scene.setUserId(user.getId());
            scene.setCode(code);
            return sceneMService.exchangeScene(scene);
        }
        return 0;
    }

}
