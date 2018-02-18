package cn.scene;

import cn.scene.model.User;
import cn.scene.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
