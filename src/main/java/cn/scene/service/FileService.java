package cn.scene.service;

import cn.scene.model.Sysfile;
import cn.scene.model.Upfile;

import java.util.List;

/**
 * 图片素材
 */
public interface FileService {

    //素材上传
    int upload(String type,String url);

    //分页素材查询
    List<Sysfile> info(String type, int page);

    //分页查询记录素材
    List<Upfile> recordInfo(String type, int page, int userId);

    //素材添加
    int addInfo(int userId,String type,String url);

}
