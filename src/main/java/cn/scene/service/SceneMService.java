package cn.scene.service;

import cn.scene.model.Scene;

import java.util.List;

/**
 * 场景管理
 */
public interface SceneMService {

    //上架
    Boolean shelve(Integer sceneId,Integer jifen);

    //设置
    Scene setting(Integer sceneId);

    //完成设置
    int issue(Scene scene);

    //删除模板
    Boolean delete(Integer sceneId);

    //下架
    int updateIssue(Integer id);

}
