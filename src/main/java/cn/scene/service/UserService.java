package cn.scene.service;

import cn.scene.model.User;

/**
 * 用户
 */
public interface UserService {

    //查询用户信息
    User selectUserInfo(Integer id);
}
