package cn.scene;

import cn.scene.model.Scene;
import cn.scene.service.SceneMService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景设置测试类
 */
public class SceneMTest extends BaseJunit4Test{

    @Autowired
    private SceneMService sceneMService;

    @Test
    public void setting(){
        String temp = "1";
        String regx = "^[0-9]+$";
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int sceneId = Integer.parseInt(temp);
            Scene scene = sceneMService.setting(sceneId);
            System.out.println(scene);
        }
    }

    @Test
    public void delete(){
        String temp = "1";
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int sceneId = Integer.parseInt(temp);
            result = sceneMService.delete(sceneId);
        }
        System.out.println(result);
    }

    @Test
    public void list(){
        String temp = "1";
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(temp) && temp.matches(regx)){
            int userId = Integer.parseInt(temp);
            list = sceneMService.sceneList(userId);
        }
        System.out.println(list);
    }
}
