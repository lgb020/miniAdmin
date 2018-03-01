package cn.scene.serviceImpl;

import cn.scene.dao.SysfileMapper;
import cn.scene.dao.UpfileMapper;
import cn.scene.model.Sysfile;
import cn.scene.model.Upfile;
import cn.scene.service.FileService;
import com.github.pagehelper.PageHelper;
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
     * 素材上传
     * @param type
     * @param url
     * @return
     */
    @Override
    @Transactional
    public int upload(String type, String url) {
        Sysfile file = new Sysfile();
        file.setType(type);
        file.setUrl(url);
        return sysfileMapper.insertSelective(file);
    }

    /**
     * 分页素材查询
     * @param type
     * @param page
     * @return
     */
    @Override
    public List<Sysfile> info(String type, int page) {
        PageHelper.startPage(page,12);
        List<Sysfile> list = sysfileMapper.selectInfoByType(type);
        return list;
    }

    /**
     * 素材记录
     * @param type
     * @param page
     * @param userId
     * @return
     */
    @Override
    public List<Upfile> recordInfo(String type, int page, int userId) {
        PageHelper.startPage(page,12);
        List<Upfile> list = upfileMapper.selectInfoByType(userId,type);
        return list;
    }

    /**
     * 素材添加
     * @param type
     * @param url
     * @return
     */
    @Override
    public int addInfo(int userId,String type, String url) {
        Upfile file = new Upfile();
        file.setId(userId);
        file.setType(type);
        file.setUrl(url);
        int result = upfileMapper.insertSelective(file);
        return result;
    }

}
