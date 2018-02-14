package cn.scene.serviceImpl;

import cn.scene.dao.MessageMapper;
import cn.scene.dao.SceneMapper;
import cn.scene.model.Message;
import cn.scene.model.Scene;
import cn.scene.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关实现类
 */
@Service("aboutService")
public class AboutServiceImpl implements AboutService{

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SceneMapper sceneMapper;

    /**
     * 通知信息
     * @param userId
     * @return
     */
    @Override
    public List<Message> selectInfo(Integer userId) {
        return messageMapper.selectByUserId(userId);
    }

    /**
     * 我的模板
     * @param userId
     * @return
     */
    @Override
    public List<Scene> selectScene(Integer userId,Integer fromId) {
        return sceneMapper.selectByFromScene(userId,fromId);
    }

}
