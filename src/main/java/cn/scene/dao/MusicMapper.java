package cn.scene.dao;

import cn.scene.model.Music;

import java.util.List;

public interface MusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    //查询音乐数据列表
    List<Music> selectByIsDel();

    //音乐搜索
    List<Music> selecByName(String content);
}