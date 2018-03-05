package cn.scene.controller;

import cn.scene.model.User;
import cn.scene.model.UserAuth;
import cn.scene.service.UserService;
import cn.scene.util.MailUtil;
import cn.scene.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            //检查账户是否存在和激活状态
            UserAuth auth = userService.selectUserAuth(account);
            if(auth!=null && auth.getStatus()==true && auth.getPassword()==password){
                User user = userService.selectUserInfo(auth.getUserId());
                request.getSession().setAttribute("user",user);
                return 1;
            }
        }
        return 0;
    }

    //注册
    @RequestMapping("/register")
    public @ResponseBody int register(HttpServletRequest request) throws Exception{
        String account = request.getParameter("account");
        String password = Md5Util.md5(request.getParameter("password"));
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            //查询用户是否存在,存在返回0
            UserAuth user = userService.selectUserAuth(account);
            if(user==null){
                user = new UserAuth();
                user.setAccount(account);
                user.setPassword(password);
                user = MailUtil.activateMail(user);
                //添加新用户
                userService.insertUserAuthInfo(user);
                return 1;
            }
        }
        return 0;
    }

    //邮件激活
    @RequestMapping("/activate")
    public String mail(HttpServletRequest request,Model model){
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        int result = userService.userActivice(token,email);
        String mess = "";
        if (result > 0) {
            mess = "激活成功，MINISCENE为你打造个性化场景";
        }else{
            mess = "时间超时或验证码已失效，请重新注册。";
        }
        model.addAttribute("mess",mess);
        return "mail";
    }

    //退出登陆
    @RequestMapping("/logout")
    public @ResponseBody int logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return 1;
    }


}
