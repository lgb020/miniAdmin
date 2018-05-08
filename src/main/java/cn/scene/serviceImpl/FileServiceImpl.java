package cn.scene.serviceImpl;

import cn.scene.dao.SysfileMapper;
import cn.scene.dao.UpfileMapper;
import cn.scene.model.Sysfile;
import cn.scene.model.Upfile;
import cn.scene.service.FileService;
import cn.scene.util.DateFormat;
import cn.scene.util.ImgEcoding;
import cn.scene.util.RealPathTool;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 背景图上传实现类
 */
@Service("fileService")
public class FileServiceImpl implements FileService{

    @Autowired
    private SysfileMapper sysfileMapper;
    @Autowired
    private UpfileMapper upfileMapper;

    /**
     * 分页素材查询
     * @param type
     * @param page
     * @return
     */
    @Override
    public List<Sysfile> info(String type, int page) {
        PageHelper.startPage(page,30);
        List<Sysfile> list = sysfileMapper.selectInfoByType(type);
        return list;
    }

    /**
     * 查询素材总数
     * @param type
     * @return
     */
    @Override
    public int infoCount(String type) {
        int counts = sysfileMapper.selectInfoCounts(type);
        int page = 0;
        if(counts%30==0){
            page = counts/30;
        }else{
            page = counts/30+1;
        }
        return page;
    }

    /**
     * 素材记录
     * @param type
     * @param userId
     * @return
     */
    @Override
    public List<Upfile> recordInfo(String type, int userId) {
        List<Upfile> list = upfileMapper.selectInfoByType(userId,type);
        return list;
    }

    /**
     * 素材添加
     * @param userId
     * @param type
     * @param img
     * @return
     */
    @Override
    @Transactional
    public int addInfo(int userId,String type, String img) {
        String path = "";
        String url = "";
        String date = DateFormat.format(new Date());
        if(type.equals("0")){
            path = RealPathTool.getRootPath()+"/upload/bg/user/"+date;
        }else {
            path = RealPathTool.getRootPath()+"/upload/img/user/"+date;
        }
        //文件上传
        try{
            String name = ImgEcoding.GenerateImage(img,path);
            if(StringUtils.isNotBlank(name)){
                String root = "http://www.hsfeng.cn/scene/upload/";
                if(type.equals("0")){
                    url = root+"bg/user/"+date+"/"+name;
                }else{
                    url = root+"img/user/"+date+"/"+name;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        //插入数据到数据库里
        int result = upfileMapper.insertInfo(userId,type,url,new Date());
        return result;
    }

    /**
     * 系统素材添加
     * @param type
     * @param url
     * @return
     */
    @Override
    public int addSysInfo(String type, String url) {
        Sysfile file = new Sysfile();
        file.setType(type);
        file.setUrl(url);
        return sysfileMapper.insertSelective(file);
    }

    @Override
    public int addFile(String type, String img) {
        Sysfile sysfile = new Sysfile();
        String path = "";
        String url = "";
        String date = DateFormat.format(new Date());
        if(type.equals("0")){
            path = RealPathTool.getRootPath()+"/upload/bg/admin/"+date;
        }else {
            path = RealPathTool.getRootPath()+"/upload/img/admin/"+date;
        }
        //文件上传
        try{
            String name = ImgEcoding.GenerateImage(img,path);
            if(StringUtils.isNotBlank(name)){
                String root = "http://www.hsfeng.cn/scene/upload/";
                if(type.equals("0")){
                    url = root+"bg/admin/"+date+"/"+name;
                }else{
                    url = root+"img/admin/"+date+"/"+name;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        sysfile.setType(type);
        sysfile.setUrl(url);
        //插入数据到数据库里
        int result = sysfileMapper.insertFile(sysfile);
        return result;
    }

    @Override
    public int deleteFile(int id) {
        int i= sysfileMapper.deleteFile(id);
        return i;
    }

}
