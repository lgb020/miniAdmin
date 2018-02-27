package cn.scene.serviceImpl;

import cn.scene.dao.MusicMapper;
import cn.scene.jedis.JedisClient;
import cn.scene.model.Music;
import cn.scene.service.MuService;
import cn.scene.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 音乐
 */
@Service("muService")
public class MuServiceImpl implements MuService {

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private JedisClient jedisClient; //redis客户端
    @Value("${MUSIC}")
    private String MUSIC;

    /**
     * 音乐列表
     * @param page
     * @return
     */
    @Override
    public List<Music> list(Integer page) {
        try{
            String field = page.toString();
            String music = jedisClient.hget(MUSIC,field);
            //判断不为空
            if(StringUtils.isNotBlank(music)){
                return JsonUtils.jsonToList(music,Music.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PageHelper.startPage(page,20); //分页插件
        List<Music> list = musicMapper.selectByIsDel();
        //把查询数据添加到redis里
        try{
            String field = page.toString();
            jedisClient.hset(MUSIC,field,JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }
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

    /**
     * 音乐总页数
     * @return
     */
    @Override
    public Integer allPage() {
        int count = musicMapper.selectCountById();
        int allPage = 0;
        if(count%20==0){
            allPage = count/20;
        }else{
            allPage = count/20+1;
        }
        return allPage;
    }
}
