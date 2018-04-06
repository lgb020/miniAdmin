package cn.scene.serviceImpl;

import cn.scene.dao.StoreMapper;
import cn.scene.jedis.JedisClient;
import cn.scene.model.Store;
import cn.scene.service.StoreService;
import cn.scene.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设计小店服务实现类
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreMapper storeMapper; //设计小店
    @Value("${STORE}")
    private String STORE;
    @Resource
    private JedisClient jedisClient; //redis客户端

    /**
     * 分页查询新入驻小店信息
     * @param page
     * @return
     */
    @Override
    @Transactional
    public List<Store> selectNewStore(int page) {
        String field = "STORE"+page; //stroe的key
        try{
            String info = jedisClient.hget(STORE,field);
            if(StringUtils.isNotBlank(info)){
                return JsonUtils.jsonToList(info,Store.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PageHelper.startPage(page,8); //pageHelper插件分页查询
        List<Store> news = storeMapper.selectInfoByPage();
        //把数据添加到redis中
        try{
            jedisClient.hset(STORE,field,JsonUtils.objectToJson(news));
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    /**
     * 小店总页数
     * @return
     */
    @Override
    public int selectStoreCounts() {
        int count = storeMapper.selectPageNumber();
        int pages = 0;
        if(count%16==0){
            pages = count/16;
        }else{
            pages = count/16+1;
        }
        return pages;
    }

    /**
     * 推荐小店
     * @param page
     * @return
     */
    @Override
    @Transactional
    public List<Store> selectRecordStroe(int page) {
        int pageCount = selectStoreCounts();
        if(page>pageCount){
            return null;
        }else{
            PageHelper.startPage(page,8); //pageHelper插件分页查询
            List<Store> record = storeMapper.selectInfoByPage();
            //根据用户id查询模板总数
            for(int i=0;i<record.size();i++){
                int count = storeMapper.selectCount(record.get(i).getId());
                record.get(i).setCounts(count);
            }
            //冒泡排序实现对list进行排序
            Store cStore = new Store();
            for(int i=0;i<record.size();i++){
                int temp = i;
                for(int j=i;j<record.size();j++){
                    if(record.get(temp).getCounts()<record.get(j).getCounts()){
                        temp = j;
                    }
                }
                if(temp!=i){
                    cStore = record.get(i);
                    record.set(i,record.get(temp)) ;
                    record.set(temp,cStore);
                }
            }
            return record;
        }
    }


}
