package cn.scene.serviceImpl;

import cn.scene.dao.MusicMapper;
import cn.scene.dao.SceneMapper;
import cn.scene.jedis.JedisClient;
import cn.scene.model.Music;
import cn.scene.service.MuService;
import cn.scene.util.DateFormat;
import cn.scene.util.JsonUtils;
import cn.scene.util.RealPathTool;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 音乐
 */
@Service("muService")
public class MuServiceImpl implements MuService {

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SceneMapper sceneMapper;
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
    @Transactional
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

    /**
     * 更新场景音乐
     * @param id
     * @param music
     * @param mTitle
     * @return
     */
    @Override
    @Transactional
    public int updateMusic(int id, String music, String mTitle) {
        return sceneMapper.updateMusicById(id,music,mTitle);
    }

    /**
     * 音乐上传
     * @param id
     * @param mTitle
     * @param request
     * @return
     */
    @Override
    @Transactional
    public int uploadMusic(int id, String mTitle, HttpServletRequest request) throws Exception{
        int result = 0;
        if(request instanceof MultipartHttpServletRequest){
            MultipartFile items =  ((MultipartHttpServletRequest) request).getFile("music");
            String date = DateFormat.format(new Date());
            String path = RealPathTool.getRootPath()+"/upload/music/"+date;
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            //判断文件格式
            if(items!=null){
                String name = items.getOriginalFilename();
                name = UUID.randomUUID() + name.substring(name.lastIndexOf("."));
                if(name.endsWith(".mp3") || name.endsWith(".MP3") || name.endsWith(".mav") || name.endsWith(".MAV")
                        || name.endsWith(".amr") || name.endsWith(".AMR")){
                    File file = new File(path,name);
                    //文件上传
                    items.transferTo(file);
                }
                String music = "http://www.hsfeng.cn/scene/upload/music/"+date+"/"+name;
                //更新数据
                result = sceneMapper.updateMusicById(id,music,mTitle);
            }
        }
        return result;
    }
}
