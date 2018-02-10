package cn.scene.serviceImpl;

import cn.scene.dao.SceneMapper;
import cn.scene.model.Scene;
import cn.scene.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 场景实现类
 */
@Service("secneService")
public class SceneServiceImpl implements SceneService{

    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public List<Scene> sceneInfo() {
        return null;
    }
}
