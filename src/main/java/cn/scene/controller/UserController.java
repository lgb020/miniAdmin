package cn.scene.controller;

import cn.scene.model.User;
import cn.scene.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String user(HttpServletRequest request,Model model){
        User user = userService.selectInfo(1);
        model.addAttribute("user",user);
        return "info";
    }
}
