package cn.scene.service;

import cn.scene.model.Store;

import java.util.List;

/**
 * 设计小店服务
 */
public interface StoreService {

    //分页查询新入驻小店信息
    List<Store> selectNewStore(int page);

    //小店总页数
    int selectStoreCounts();

    //推荐小店
    List<Store> selectRecordStroe(int page);

}
