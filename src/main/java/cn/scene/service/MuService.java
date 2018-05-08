package cn.scene.service;

import cn.scene.model.Music;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 音乐
 */
public interface MuService {

    //在线音乐
    List<Music> list(Integer page);

    //音乐查找
    List<Music> search(String content);

    //音乐插入
    Integer insert(String name,String url,String length);

    //音乐总页数
    Integer allPage();

    //更新场景音乐
    int updateMusic(int id, String music,String mTitle);

    //音乐文件上传
    int uploadMusic(int id, String mTitle, HttpServletRequest request) throws Exception;

    //音乐列表
    List<Music> musicList(Integer page);

    //删除音乐
    int deleteMusic(int id);

}
