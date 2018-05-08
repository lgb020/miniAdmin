package cn.scene.dao;

import cn.scene.model.Music;
import org.apache.ibatis.annotations.Param;

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

    //音乐插入
    int musicInsert(@Param("name")String name,@Param("url")String url,@Param("length")String length);

    //根据id查询总数
    int selectCountById();

    //查询音乐列表
    List<Music> selectMusic();

    //删除音乐
    int deleteMusic(Integer id);
}