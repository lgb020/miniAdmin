package cn.scene.controller;

import cn.scene.jedis.JedisClient;
import cn.scene.model.User;
import cn.scene.model.UserAuth;
import cn.scene.service.UserService;
import cn.scene.util.MailUtil;
import cn.scene.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JedisClient jedisClient; //redis客户端

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
        String password = Md5Util.md5(request.getParameter("password"));
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            //检查账户是否存在和激活状态
            UserAuth auth = userService.selectUserAuth(account);
            if(auth!=null && auth.getStatus()==true && auth.getPassword().equals(password)){
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
            if(user==null) {
                user = new UserAuth();
                user.setAccount(account);
                user.setPassword(password);
                user = MailUtil.activateMail(user);
                //邮箱验证添加新用户
                userService.insertUserAuthInfo(user, "email");
                return 1;
            }else if(user.getStatus()==false){
                //还没激活,发送激活邮件
                MailUtil.activateMail(user);
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

    //重置密码获取邮箱验证码
    @RequestMapping("/code")
    @Transactional
    public @ResponseBody int resetCode(HttpServletRequest request) throws Exception{
        String account = request.getParameter("account");
        if(StringUtils.isNotBlank(account)) {
            //检查账户是否存在
            UserAuth auth = userService.selectUserAuth(account);
            if(auth!=null){
                String code = UUID.randomUUID().toString().substring(0,6);
                //redis保存验证码
                jedisClient.set(account,code);
                jedisClient.expire(account,300); //生命周期5分钟
                int result = MailUtil.codeMail(account,code);
                return result;
            }
        }
        return 0;
    }

    //重置密码
    @RequestMapping("/reset")
    public @ResponseBody int reset(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = Md5Util.md5(request.getParameter("password"));
        String code = request.getParameter("code");
        String sysCode = jedisClient.get(account);
        //判断验证码
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password) &&
                StringUtils.isNotBlank(code) && StringUtils.isNotBlank(sysCode) &&
                code.equals(sysCode)){
            //查询用户是否存在,存在返回0
            UserAuth user = userService.selectUserAuth(account);
            if(user!=null && user.getStatus()==true) {
                user.setPassword(password);
                //未激活找回密码通过验证码默认激活
                user.setStatus(true);
                //更新用户的密码
                int result = userService.updateInfo(user);
                if(result==1){
                    //清除验证码
                    jedisClient.del(account);
                }
                return result;
            }
        }
        return 0;
    }

    //退出登陆
    @RequestMapping("/logout")
    public @ResponseBody int logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return 1;
    }


}
