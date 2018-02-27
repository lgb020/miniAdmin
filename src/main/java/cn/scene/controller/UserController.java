package cn.scene.controller;

import cn.scene.model.User;
import cn.scene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //判断用户是否登录
    @RequestMapping("/auth")
    public @ResponseBody int isLogin(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null){
            return 1;
        }
        return 0;
    }

    //获取登陆用户信息
    @RequestMapping("/info")
    public @ResponseBody User info(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return user;
    }

    //登录
    @RequestMapping("/login")
    public @ResponseBody int login(HttpServletRequest request){
        User user = userService.selectUserInfo(1);
        request.getSession().setAttribute("user",user);
        return 1;
    }

    //退出登陆
    @RequestMapping("/logout")
    public @ResponseBody int logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return 1;
    }


}
