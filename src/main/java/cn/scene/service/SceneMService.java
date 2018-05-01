package cn.scene.service;

import cn.scene.model.Scene;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    //场景举报ip查询
    Boolean ipIsExit(int sceneId,String ip);

    //场景举报
    int reportScene(int sceneId,String ip,String content);

    //场景兑换
    int exchangeScene(Scene scene);

    //场景数据导出
    XSSFWorkbook exportExcelInfo(int sceneId) throws Exception;

}
