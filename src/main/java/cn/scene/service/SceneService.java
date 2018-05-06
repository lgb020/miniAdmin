package cn.scene.service;

import cn.scene.model.Scene;
import cn.scene.model.ScenePage;
import cn.scene.model.User;

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

    //更新封面图
    int updateCover(String cover,int id);

    //场景搜索
    List<Scene> search(String content);

    //场景查询
    Scene scene(Integer sceneId);

    //单页查询
    List<ScenePage> pageInfo(Integer sceneId);

    //根据code查看场景
    Scene scene(String code);

    //场景兑换
    int exchangeScene(User user, Integer sceneId);

    //查询共享场景总数
    int IssueTotal(int userId);

}
