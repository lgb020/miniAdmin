package cn.scene;

import cn.scene.model.Scene;
import cn.scene.service.SceneService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景测试类
 */
public class SceneTest extends BaseJunit4Test{

    @Autowired
    private SceneService sceneService;

    @Test
    public void info(){
        String index = "0";
        String regx = "^1|0$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page = Integer.parseInt(index);
            if(page==0){
                list = sceneService.sceneInfo(0);
            }else{
                list = sceneService.sceneInfo(1);
            }
        }
        System.out.println(list);
    }

    @Test
    public void news(){
        List<Scene> list = sceneService.selectNews();
        System.out.println(list);
    }

    @Test
    public void hotScene(){
        String index = "1";
        String regx = "^1|0$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page = Integer.parseInt(index);
            if(page==0){
                list = sceneService.hotInfo(0);
            }else{
                list = sceneService.hotInfo(1);
            }
        }
        System.out.println(list);
    }
}
