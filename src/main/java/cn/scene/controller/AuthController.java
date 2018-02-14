package cn.scene.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限管理
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    //登陆状态检查
    @RequestMapping("/index")
    public @ResponseBody Integer index(HttpServletRequest request){
        String account = request.getSession().getAttribute("account").toString();
        //用户存在返回1,否者返回0
        if(StringUtils.isNotBlank(account)){
            return 1;
        }else{
            return 0;
        }
    }


}
