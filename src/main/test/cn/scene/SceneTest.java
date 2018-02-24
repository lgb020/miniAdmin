package cn.scene;

import cn.scene.model.Scene;
import cn.scene.service.SceneService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void hotSceneByTime(){
        String index = "2";
        String regx = "^[0-9]+$";
        List<Scene> list = new ArrayList<>();
        if(index.matches(regx)){
            int page = Integer.parseInt(index);
            list = sceneService.hotPage(page);
        }
        System.out.println(list);
    }

    @Test
    public void type(){
        String temp = "3";
        String charge = "1"; //1-积分兑换,0-免积分
        String regx = "^[0-9]$";
        String cRegx = "^0|1$";
        List<Scene> list = new ArrayList<>();
        if(StringUtils.isNotBlank(temp) && StringUtils.isNotBlank(charge) && temp.matches(regx) &&charge.matches(cRegx)){
            int type = Integer.parseInt(temp);
            int isCharge = Integer.parseInt(charge);
            list = sceneService.TypeScene(type,isCharge);
        }
        System.out.println(list);
    }

    @Test
    public void insertNews(){
        Scene scene = new Scene();
        scene.setUserId(1);
        scene.setFromScene(2);
        sceneService.init(scene);
        int id = scene.getId();
        System.out.println(id);
    }
}
