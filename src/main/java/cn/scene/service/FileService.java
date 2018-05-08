package cn.scene.service;

import cn.scene.model.Sysfile;
import cn.scene.model.Upfile;

import java.util.List;

/**
 * 图片素材
 */
public interface FileService {

    //分页素材查询
    List<Sysfile> info(String type, int page);

    //查询素材总页数
    int infoCount(String type);

    //查询素材记录
    List<Upfile> recordInfo(String type,int userId);

    //用户素材添加
    int addInfo(int userId,String type,String img);

    //系统素材添加
    int addSysInfo(String type,String url);

    //后台添加素材
    int addFile(String type,String img);

    //后台删除素材
    int deleteFile(int id );

}
