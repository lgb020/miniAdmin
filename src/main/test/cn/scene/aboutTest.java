package cn.scene;

import cn.scene.model.Message;
import cn.scene.model.Scene;
import cn.scene.service.AboutService;
import cn.scene.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心测试
 */
public class aboutTest extends BaseJunit4Test{

    @Autowired
    private AboutService aboutService;
    @Autowired
    private UserService userService;

    @Test
    public void message(){
        String index = "1";
        String regx = "^[0-9]+$";
        List<Message> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int userId = Integer.parseInt(index);
            list = aboutService.selectInfo(userId);
        }
        System.out.println(list);
    }

    @Test
    public void makeScene(){
        String index = "1";
        //0为原创,1为非原创
        String temp = "1";
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx) && temp.matches(regx)){
            int userId = Integer.parseInt(index);
            int fromId = Integer.parseInt(temp);
            list = aboutService.selectScene(userId,fromId);
        }
        System.out.println(list);
    }

    @Test
    public void read(){
        String index = "1";
        String regx = "^[0-9]+$";
        int result = 0;
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int id = Integer.parseInt(index);
            result = userService.updateRead(id);
        }
        System.out.println(result);
    }

}
