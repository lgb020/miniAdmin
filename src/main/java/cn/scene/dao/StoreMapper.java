package cn.scene.dao;


import cn.scene.model.Store;

import java.util.List;

public interface StoreMapper {

    //查询入驻小店
    List<Store> selectInfoByPage();

    //根据用户id查询模板总数
    int selectCount(int userId);

    //小店总页数
    int selectPageNumber();

}
