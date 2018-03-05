package cn.scene.serviceImpl;

import cn.scene.dao.UserAuthMapper;
import cn.scene.dao.UserMapper;
import cn.scene.model.User;
import cn.scene.model.UserAuth;
import cn.scene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthMapper userAuthMapper;

    /**
     * 用户信息
     * @param id
     * @return
     */
    @Override
    public User selectUserInfo(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新通知状态
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int updateRead(Integer id) {
        return userMapper.updateByIsRade(id);
    }

    /**
     * 检查用户是否存在
     * @param account
     * @return
     */
    @Override
    public UserAuth selectUserAuth(String account) {
        return userAuthMapper.selectInfoByAccount(account);
    }

    /**
     * 添加新的用户
     * @param auth
     * @return
     */
    @Override
    @Transactional
    public int insertUserAuthInfo(UserAuth auth) {
        return userAuthMapper.insertSelective(auth);
    }

    /**
     * 邮件激活
     * @param token
     * @param email
     * @return
     */
    @Override
    @Transactional
    public int userActivice(String token, String email) {
        Long time = System.currentTimeMillis();
        UserAuth user = userAuthMapper.selectInfoByAccount(email);
        if(user!=null){
            if(time<user.getTimes() && token.equals(user.getToken())){
                token = token.replace("1","m"); //重置token,防止被其他利用
                //更新激活状态
                int result = userAuthMapper.updateByStatus(email,token);
                return result;
            }else{
                //超时或者验证码不正确
                return -1;
            }
        }
        return 0;
    }
}
