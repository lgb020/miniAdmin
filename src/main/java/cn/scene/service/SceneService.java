package cn.scene.service;

import cn.scene.model.Scene;

import java.util.List;

/**
 * 场景
 */
public interface SceneService {

    //精选模板
    List<Scene> sceneInfo(Integer index);

    //最新推荐
    List<Scene> selectNews();

    //最新热门模板
    List<Scene> hotInfo(Integer index);

    //热门推荐
    List<Scene> hotPage(Integer page);

    //热销排行
    List<Scene> hotSell(Integer page);

}
