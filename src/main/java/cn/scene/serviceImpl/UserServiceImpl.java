package cn.scene.serviceImpl;

import cn.scene.dao.UserMapper;
import cn.scene.model.User;
import cn.scene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户信息
     * @param id
     * @return
     */
    @Override
    public User selectUserInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
