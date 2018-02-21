package cn.scene.serviceImpl;

import cn.scene.dao.MusicMapper;
import cn.scene.model.Music;
import cn.scene.service.MuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐
 */
@Service("muService")
public class MuServiceImpl implements MuService {

    @Autowired
    private MusicMapper musicMapper;

    /**
     * 音乐列表
     * @param page
     * @return
     */
    @Override
    public List<Music> list(Integer page) {
        PageHelper.startPage(page,20); //分页插件
        List<Music> list = musicMapper.selectByIsDel();
        return list;
    }

    /**
     * 音乐查找
     * @param content
     * @return
     */
    @Override
    public List<Music> search(String content) {
        List<Music> list = musicMapper.selecByName(content);
        return list;
    }

    /**
     * 音乐插入
     * @param name
     * @param url
     * @param length
     * @return
     */
    @Override
    public Integer insert(String name,String url,String length) {
        return musicMapper.musicInsert(name,url,length);
    }
}
