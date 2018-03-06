package cn.scene;

import cn.scene.model.User;
import cn.scene.model.UserAuth;
import cn.scene.service.UserService;
import cn.scene.util.MailUtil;
import cn.scene.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 用户测试
 */
public class UserTest extends BaseJunit4Test{

    @Autowired
    private UserService userService;

    @Test
    public void user(){
        String index = "1";
        String regx = "^[0-9]+$";
        User user = new User();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int userId = Integer.parseInt(index);
            user = userService.selectUserInfo(userId);
        }
        System.out.println(user);
    }

    @Test
    public void login() throws Exception{
        String account = "1083178465@qq.com";
        String password = Md5Util.md5("123456");
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            //查询用户是否存在,存在返回-1
            UserAuth user = userService.selectUserAuth(account);
            if(user==null){
                user = new UserAuth();
                user.setAccount(account);
                user.setPassword(password);
                user = MailUtil.activateMail(user);
                System.out.println(user);
                //添加新用户
                //userService.insertUserAuthInfo(user);
            }
        }
    }

    @Test
    public void uuid(){
        String code = UUID.randomUUID().toString().substring(0,6);
        System.out.println(code);
    }

    @Test
    public void getcode(){
        String account = "1083178465@qq.com";
        if(StringUtils.isNotBlank(account)) {
            //检查账户是否存在
            UserAuth auth = userService.selectUserAuth(account);
            if(auth!=null){
                String code = UUID.randomUUID().toString().substring(0,6);
                System.out.println(code);
            }
        }
    }

    @Test
    public void resetPass(){
        String account = "1083178465@qq.com";
        String password = Md5Util.md5("123qweasdzxc");
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)){
            //查询用户是否存在,存在返回0
            UserAuth user = userService.selectUserAuth(account);
            if(user!=null && user.getStatus()==true) {
                user.setPassword(password);
                //更新用户的密码
                int result = userService.updateInfo(user);
                System.out.println(result);
            }
        }
    }
}
