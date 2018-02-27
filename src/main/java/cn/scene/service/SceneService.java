package cn.scene.service;

import cn.scene.model.Scene;
import cn.scene.model.ScenePage;

import java.util.List;

/**
 * 场景
 */
public interface SceneService {

    //精选模板
    List<Scene> sceneInfo(Integer page);

    //精选模板总数
    int count();

    //最新推荐
    List<Scene> selectNews();

    //热门模板-最新
    List<Scene> hotInfo(Integer page);

    //热门模板-最新总数
    int hCount();

    //热门模板-热门推荐
    List<Scene> hotPage(Integer page);

    //热门推荐-总页数
    int hotPageCount();

    //热销排行
    List<Scene> companyScene(Integer page);

    //热销排行总数
    int companySceneCount();

    //个人相册
    List<Scene> photoScene(Integer page);

    //个人相册总数
    int photoSceneCount();

    //分类查询
    List<Scene> TypeScene(Integer type, Integer isCharge);

    //添加数据返回id
    int init(Scene scene);

    //发布场景
    int insert(ScenePage scenePage);

}
