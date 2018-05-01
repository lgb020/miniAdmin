package cn.scene.serviceImpl;

import cn.scene.dao.MessageMapper;
import cn.scene.dao.SceneMapper;
import cn.scene.model.Message;
import cn.scene.model.Scene;
import cn.scene.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 后台管理
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 违规场景删除
     * @param sceneId
     * @return
     */
    @Override
    @Transactional
    public int deleteScene(Integer sceneId) {
        //查询违规场景基本信息
        Scene scene = sceneMapper.selectByApartInfo(sceneId);
        //删除
        int result = sceneMapper.updateIsDel(sceneId);
        if(result>0){
            //警告原创作者
            Message message = new Message();
            message.setUserId(scene.getUserId());
            message.setTimes(new Date());
            message.setContent("场景“"+scene.getTitle()+"”内容不良，已被管理员删除，如有疑问，可联系管理员。");
            return messageMapper.insertSelective(message);
        }
        return 0;
    }


}
