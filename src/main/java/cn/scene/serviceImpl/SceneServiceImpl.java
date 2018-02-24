package cn.scene.serviceImpl;

import cn.scene.dao.SceneMapper;
import cn.scene.dao.ScenePageMapper;
import cn.scene.model.Scene;
import cn.scene.model.ScenePage;
import cn.scene.service.SceneService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 场景实现类
 */
@Service("secneService")
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ScenePageMapper scenePageMapper;

    /**
     * 精选模板
     * @param index
     * index==1,换一批显示,根据精选场景的被使用热度进行筛选
     */
    @Override
    public List<Scene> sceneInfo(Integer index) {
        int page = 1; //默认是第一页
        if(index==1){
            int count = sceneMapper.selectCount(); //查询总数
            Double number = Math.random();
            if (number >= 0.5) {
                page = (int) (Math.random() + 2) * (count - 6);
            } else {
                page = (int) (Math.random() + 1) * (count - 6);
            }
        }
        PageHelper.startPage(page,6);
        return sceneMapper.selectDelicate();
    }

    /**
     * 最新推荐
     * @return
     */
    @Override
    public List<Scene> selectNews() {
        return sceneMapper.selectNews();
    }

    /**
     * 最新热门模板
     * @param index
     * index==1,换一批显示,根据上架场景的热度进行筛选
     */
    @Override
    public List<Scene> hotInfo(Integer index) {
        int page = 0;
        if(index==1){
            int count = sceneMapper.selectCount(); //查询总数
            Double number = Math.random();
            if (number >= 0.5) {
                page = (int) (Math.random() + 1) * (count - 6);
            } else {
                page = (int) (Math.random()) * (count - 6);
            }
        }
        PageHelper.startPage(page,9);
        return sceneMapper.selectByHot();
    }

    /**
     * 热门推荐
     * @param page 页数
     * @return
     */
    @Override
    public List<Scene> hotPage(Integer page) {
        PageHelper.startPage(page,9);
        List<Scene> list = sceneMapper.selectByTimes();
        return list;
    }

    /**
     * 企业宣传,热销排行榜
     * @param page 页数
     * @return
     */
    @Override
    public List<Scene> companyScene(Integer page) {
        PageHelper.startPage(page,12);
        List<Scene> list = sceneMapper.selectCompanyByHitCount();
        return list;
    }

    /**
     * 个人相册,热门模板
     * @param page 页数
     * @return
     */
    @Override
    public List<Scene> photoScene(Integer page) {
        PageHelper.startPage(page,12);
        List<Scene> list = sceneMapper.selectPhotoByHitCount();
        return list;
    }

    /**
     * 分类查询
     * @param type 类别id
     * @param isCharge 1-积分兑换,0-免积分
     * @return
     */
    @Override
    public List<Scene> TypeScene(Integer type, Integer isCharge) {
        List<Scene> list = new ArrayList<>();
        if(isCharge==1){
            list = sceneMapper.selectCharge(type);
        }else {
            list = sceneMapper.selectFree(type);
        }
        return list;
    }

    /**
     * 新添加数据返回id
     * @param scene
     * @return
     */
    @Override
    @Transactional
    public int init(Scene scene) {
        String code = UUID.randomUUID().toString().substring(0,8); //生成访问码
        Date times = new Date();
        scene.setCode(code);
        scene.setTimes(times);
        return sceneMapper.getNewsId(scene);
    }

    /**
     * 场景发布
     * @param scenePage
     * @return
     */
    @Override
    public int insert(ScenePage scenePage) {
        scenePage.setTimes(new Date());
        return scenePageMapper.insertSelective(scenePage);
    }

}
