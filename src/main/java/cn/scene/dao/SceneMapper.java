package cn.scene.dao;

import cn.scene.model.Scene;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scene record);

    int insertSelective(Scene record);

    Scene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);

    //查询精选模板总数
    int selectCount();

    //查询精选模板
    List<Scene> selectDelicate();

    //查询最新推荐
    List<Scene> selectNews();

    //热门模板-最新
    List<Scene> selectByHot();

    //热门模板-最新模板总数
    int selectHCount();

    //热门模板-热门推荐
    List<Scene> selectByTimes();

    //热门模板-热门推荐总数
    int selectByTimesCount();

    //企业宣传热销排行榜
    List<Scene> selectCompanyByHitCount();

    //企业宣传总数
    int selectCompanyCount();

    //个人相册热销模板
    List<Scene> selectPhotoByHitCount();

    //个人相册总数
    int selectPhotoCount();

    //分类查询,积分兑换
    List<Scene> selectCharge(Integer types);

    //分类查询,免费
    List<Scene> selectFree(Integer types);

    //个人中心查询我的模板
    List<Scene> selectByFromScene(@Param("userId") Integer userId, @Param("fromId") Integer FromId);

    //场景上架
    int updateByIsIssue(Integer id);

    //场景标题
    Scene selectByApartInfo(Integer id);

    //场景删除
    int updateIsDel(Integer id);

    //我的小店
    List<Scene> selectByUserId(Integer userId);

    //插入数据初始化
    int getNewsId(Scene scene);
}