package cn.scene.service;

import cn.scene.model.Music;

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

}
