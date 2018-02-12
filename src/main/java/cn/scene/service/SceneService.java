package cn.scene.service;

import cn.scene.model.Scene;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
    List<Scene> companyScene(Integer page);

    //个人相册
    List<Scene> photoScene(Integer page);

    //分类查询
    List<Scene> TypeScene(Integer type, Integer isCharge);

}
