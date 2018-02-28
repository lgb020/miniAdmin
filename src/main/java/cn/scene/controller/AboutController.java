package cn.scene.controller;

import cn.scene.model.JifenRecord;
import cn.scene.model.Message;
import cn.scene.model.Scene;
import cn.scene.model.User;
import cn.scene.service.AboutService;
import cn.scene.service.JifenRecordService;
import cn.scene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;
    @Autowired
    private UserService userService;
    @Autowired
    private JifenRecordService jifenRecordService;

    //头像查询
    @RequestMapping("/index")
    public @ResponseBody User index(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return user;
    }

    //通知信息
    @RequestMapping("/message")
    public @ResponseBody List<Message> message(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Message> list = aboutService.selectInfo(user.getId());
        return list;
    }

    //更新通知信息状态
    @RequestMapping("/mess/read")
    public @ResponseBody int isRead(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        int result = userService.updateRead(user.getId());
        return result;
    }

    //删除通知信息
    @RequestMapping("/mess/delete")
    public @ResponseBody int messDelete(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            result = aboutService.deleteMess(id);
        }
        return result;
    }

    //我的模板
    @RequestMapping("/scene")
    public @ResponseBody List<Scene> makeSecne(HttpServletRequest request){
        //0-原创,1-非原创
        String temp = request.getParameter("temp");
        String tRegx = "^0|1$";
        List<Scene> list = new ArrayList<>();
        if(temp.matches(tRegx)){
            int fromId = Integer.parseInt(temp);
            User user = (User)request.getSession().getAttribute("user");
            list = aboutService.selectScene(user.getId(),fromId);
        }
        return list;
    }

    //我的小店
    @RequestMapping("/store")
    public @ResponseBody List<Scene> store(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Scene> list = aboutService.sceneList(user.getId());
        return list;
    }

    //会员信息,积分记录
    @RequestMapping("/member")
    public @ResponseBody List<JifenRecord> jifenInfo(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        String index = request.getParameter("type");
        String regx = "^[0-9]+$";
        List<JifenRecord> list = new ArrayList<>();
        if(index.matches(regx)) {
            int temp = Integer.parseInt(index);
            list = jifenRecordService.selectJifen(user.getId(),temp);
        }
        return list;
    }

}
