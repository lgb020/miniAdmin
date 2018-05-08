package cn.scene.serviceImpl;

import cn.scene.dao.AdvertiseMapper;
import cn.scene.jedis.JedisClient;
import cn.scene.model.Advertise;
import cn.scene.service.AdvService;
import cn.scene.util.DateFormat;
import cn.scene.util.ImgEcoding;
import cn.scene.util.JsonUtils;
import cn.scene.util.RealPathTool;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 轮播图实现类
 */
@Service("advService")
public class AdvServiceImpl implements AdvService{

    @Autowired
    private AdvertiseMapper advertiseMapper;
    @Autowired
    private JedisClient jedisClient; //redis客户端
    @Value("${INDEX_ADV}")
    private String INDEX_ADV;

    public List<Advertise> info() {
        try{
            String adv = jedisClient.get(INDEX_ADV);
            //判断不为空
            if(StringUtils.isNotBlank(adv)){
                return JsonUtils.jsonToList(adv,Advertise.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Advertise> list =  advertiseMapper.selectInfo();
        try{
            jedisClient.set(INDEX_ADV,JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    public List<Advertise> adList() {
        List<Advertise> list = advertiseMapper.selectAd();
        return list;
    }

    public int insertImg(String image){
        String date = DateFormat.format(new Date());
        String path = RealPathTool.getRootPath()+"/upload/adv/"+date;
        String url = "";
        //文件上传
        try{
            String name = ImgEcoding.GenerateImage(image,path);
            if(StringUtils.isNotBlank(name)){
                String root = "http://www.hsfeng.cn/scene/upload/";
                url = root+"adv/"+date+"/"+name;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        //更新封面
        Advertise advertise = new Advertise();
        advertise.setImg(url);
        int i =advertiseMapper.insertImg(advertise);
        return i;
    }

    public int deleteAd(int id) {
        int i= advertiseMapper.deleteAd(id);
        return i;
    }

}
