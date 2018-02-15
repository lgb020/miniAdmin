package cn.scene;

import cn.scene.model.Scene;
import cn.scene.service.SceneMService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
