package cn.scene.serviceImpl;

import cn.scene.dao.MessageMapper;
import cn.scene.dao.SceneMapper;
import cn.scene.model.Message;
import cn.scene.model.Scene;
import cn.scene.service.AboutService;
import cn.scene.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        List<Message> info = messageMapper.selectByUserId(userId);
        for(int i=0;i<info.size();i++){
            Date times = info.get(i).getTimes();
            info.get(i).setsTimes(DateFormat.format(times));
        }
        return info;
    }

    /**
     * 删除通知信息
     * @param id
     * @return
     */
    @Override
    public int deleteMess(Integer id) {
        return messageMapper.updateIsDel(id);
    }

    /**
     * 我的模板
     * @param userId
     * @return
     */
    @Override
    public List<Scene> selectScene(Integer userId,Integer fromId) {
        List<Scene> list = sceneMapper.selectByFromScene(userId,fromId);
        for(int i=0;i<list.size();i++){
            Date times = list.get(i).getTimes();
            list.get(i).setsTimes(DateFormat.format(times));
        }
        return list;
    }

    /**
     * 我的小店
     * @param userId
     * @return
     */
    @Override
    public List<Scene> sceneList(Integer userId) {
        List<Scene> list = sceneMapper.selectByUserId(userId);
        for(int i=0;i<list.size();i++){
            Date date = list.get(i).getTimes();
            list.get(i).setsTimes(DateFormat.format(date));
        }
        return list;
    }


}
