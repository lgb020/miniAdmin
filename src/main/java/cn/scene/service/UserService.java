package cn.scene.service;

import cn.scene.model.User;

/**
 * 用户管理
 */
public interface UserService {

    User selectInfo(Integer id);
}
