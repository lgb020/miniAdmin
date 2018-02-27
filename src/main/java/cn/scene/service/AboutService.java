package cn.scene.service;

import cn.scene.model.Message;
import cn.scene.model.Scene;

import java.util.List;

/**
 * 用户相关
 */
public interface AboutService {

    //通知信息
    List<Message> selectInfo(Integer userId);

    //删除通知信息
    int deleteMess(Integer id);

    //我的模板
    List<Scene> selectScene(Integer userId,Integer fromId);

    //我的小店
    List<Scene> sceneList(Integer userId);
}
