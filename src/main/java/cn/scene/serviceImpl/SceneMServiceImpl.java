package cn.scene.serviceImpl;

import cn.scene.dao.SceneMapper;
import cn.scene.model.Scene;
import cn.scene.service.SceneMService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 场景管理
 */
@Service("sceneMService")
public class SceneMServiceImpl implements SceneMService{

    @Autowired
    private SceneMapper sceneMapper;

    /**
     * 上架
     * @param sceneId
     * @return
     */
    @Override
    public Boolean shelve(Integer sceneId) {
        int result = sceneMapper.updateByIsIssue(sceneId);
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
    public int issue(Scene scene) {
        return sceneMapper.updateByPrimaryKeySelective(scene);
    }

    /**
     * 删除模板
     * @param sceneId
     * @return
     */
    @Override
    public Boolean delete(Integer sceneId) {
        int temp = sceneMapper.updateIsDel(sceneId);
        if(temp>0){
            return true;
        }
        return false;
    }


}
