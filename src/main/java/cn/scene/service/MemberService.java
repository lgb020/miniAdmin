package cn.scene.service;

import cn.scene.model.JifenRecord;

import java.util.List;

/**
 * 会员
 */
public interface MemberService {

    //积分兑换记录
    List<JifenRecord> selectJifen(Integer userId);
}
