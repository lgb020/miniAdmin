package cn.scene.service;

import cn.scene.model.User;
import cn.scene.model.UserAuth;

/**
 * 用户
 */
public interface UserService {

    //查询用户信息
    User selectUserInfo(int id);

    //更新用户通知状态
    int updateRead(Integer id);

    //检查用户是否存在
    UserAuth selectUserAuth(String account);

    //添加新用户
    int insertUserAuthInfo(UserAuth auth,String type);

    //邮件激活
    int userActivice(String token,String email);

    //更新用户信息
    int updateInfo(UserAuth auth);

    //更新用户基本信息
    int updateUserInfo(User user);
}
