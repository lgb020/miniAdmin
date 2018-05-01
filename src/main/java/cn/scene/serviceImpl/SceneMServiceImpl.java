package cn.scene.serviceImpl;

import cn.scene.dao.SceneMapper;
import cn.scene.dao.SceneReportMapper;
import cn.scene.model.Scene;
import cn.scene.model.SceneReport;
import cn.scene.service.SceneMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 场景管理
 */
@Service("sceneMService")
public class SceneMServiceImpl implements SceneMService{

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private SceneReportMapper sceneReportMapper;

    /**
     * 上架
     * @param sceneId
     * @return
     */
    @Override
    public Boolean shelve(Integer sceneId,Integer jifen) {
        int result = sceneMapper.updateByIsIssue(sceneId,jifen);
        if(result==0){
            return false;
        }
        return true;
    }

    /**
     * 设置
     * @param sceneId
     * @return
     */
    @Override
    public Scene setting(Integer sceneId) {
        return sceneMapper.selectByApartInfo(sceneId);
    }

    /**
     * 完成设置
     * @param scene
     * @return
     */
    @Override
    @Transactional
    public int issue(Scene scene) {
        return sceneMapper.updateByPrimaryKeySelective(scene);
    }

    /**
     * 删除模板
     * @param sceneId
     * @return
     */
    @Override
    @Transactional
    public Boolean delete(Integer sceneId) {
        int temp = sceneMapper.updateIsDel(sceneId);
        if(temp>0){
            return true;
        }
        return false;
    }

    /**
     * 模板下架
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int updateIssue(Integer id) {
        return sceneMapper.updateIsIssue(id);
    }

    /**
     * 场景举报ip查询
     * @param sceneId
     * @param ip
     * @return
     */
    @Override
    public Boolean ipIsExit(int sceneId, String ip) {
        SceneReport result = sceneReportMapper.selectIdBySceneId(sceneId,ip);
        if(result!=null){
            return false; //该ip已有举报记录
        }
        return true;
    }

    /**
     * 场景举报
     * @param sceneId
     * @param ip
     * @param content
     * @return
     */
    @Override
    public int reportScene(int sceneId, String ip, String content) {
        SceneReport report = new SceneReport();
        report.setIp(ip);
        report.setSceneId(sceneId);
        report.setReason(content);
        return sceneReportMapper.insertReportInfo(report);
    }

    @Override
    public int exchangeScene(Scene scene) {
        sceneMapper.insertSelective(scene);
        return 1;
    }


}
