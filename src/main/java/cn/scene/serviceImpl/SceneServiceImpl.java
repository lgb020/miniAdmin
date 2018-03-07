package cn.scene.serviceImpl;

import cn.scene.dao.SceneMapper;
import cn.scene.dao.ScenePageMapper;
import cn.scene.model.Scene;
import cn.scene.model.ScenePage;
import cn.scene.service.SceneService;
import cn.scene.util.DateFormat;
import cn.scene.util.ImgEcoding;
import cn.scene.util.RealPathTool;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
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
@Service("sceneService")
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private ScenePageMapper scenePageMapper;

    /**
     * 精选模板
     * @param page
     * index==1,换一批显示,根据精选场景的被使用热度进行筛选
     */
    @Override
    public List<Scene> sceneInfo(Integer page) {
        PageHelper.startPage(page,6);
        List<Scene> list = sceneMapper.selectDelicate();
        return list;
    }

    /**
     * 精选模板总页数
     * @return
     */
    @Override
    public int count() {
        int count = sceneMapper.selectCount(); //查询总数
        int page = 0;
        if(count%6==0){
            page = count/6;
        }else{
            page = count/6+1;
        }
        return page;
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
     * @param page
     * index==1,换一批显示,根据上架场景的热度进行筛选
     */
    @Override
    public List<Scene> hotInfo(Integer page) {
        PageHelper.startPage(page,6);
        List<Scene> list = sceneMapper.selectByHot();
        return list;
    }

    @Override
    public int hCount() {
        int count = sceneMapper.selectHCount();
        int page = 0;
        if(count%6==0){
            page = count/6;
        }else{
            page = count/6+1;
        }
        return page;
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
     * 热门推荐总页数
     * @return
     */
    @Override
    public int hotPageCount() {
        int count = sceneMapper.selectByTimesCount();
        int page = 0;
        if(count%9==0){
            page = count/9;
        }else {
            page = count/9+1;
        }
        return page;
    }

    /**
     * 企业宣传,热销排行榜
     * @param page 页数
     * @return
     */
    @Override
    public List<Scene> companyScene(Integer page) {
        PageHelper.startPage(page,9);
        List<Scene> list = sceneMapper.selectCompanyByHitCount();
        return list;
    }

    /**
     * 销排行总数
     * @return
     */
    @Override
    public int companySceneCount() {
        int count = sceneMapper.selectCompanyCount();
        int page = 0;
        if(count%9==0){
            page = count/9;
        }else {
            page = count/9+1;
        }
        return page;
    }

    /**
     * 个人相册,热门模板
     * @param page 页数
     * @return
     */
    @Override
    public List<Scene> photoScene(Integer page) {
        PageHelper.startPage(page,9);
        List<Scene> list = sceneMapper.selectPhotoByHitCount();
        return list;
    }

    /**
     * 个人相册总数
     * @return
     */
    @Override
    public int photoSceneCount() {
        int count = sceneMapper.selectPhotoCount();
        int page = 0;
        if(count%9==0){
            page = count/9;
        }else {
            page = count/9+1;
        }
        return page;
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
    @Transactional
    public int insert(ScenePage scenePage) {
        //存在则更新，不存在则插入
        int resule= scenePageMapper.selectBySceneId(scenePage.getSceneId(),scenePage.getCurrentPage());
        scenePage.setTimes(new Date());
        if(resule>0){
            return scenePageMapper.updateByPrimaryKeySelective(scenePage);
        }else{
            //插入
            return scenePageMapper.insertSelective(scenePage);
        }
    }

    /**
     * 更新封面图
     * @param cover
     * @param id
     * @return
     */
    @Override
    public int updateCover(String cover, int id) {
        String url = "";
        String date = DateFormat.format(new Date());
        String path = RealPathTool.getRootPath()+"/upload/cover/"+date;
        //文件上传
        try{
            String name = ImgEcoding.GenerateImage(cover,path);
            if(StringUtils.isNotBlank(name)){
                String root = "http://www.hsfeng.cn/scene/upload/";
                url = root+"user/"+date+"/"+name;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        //更新封面
        int result = sceneMapper.updateCoverById(id,url);
        return result;
    }

    /**
     * 场景搜索
     * @param content
     * @return
     */
    @Override
    public List<Scene> search(String content) {
        return sceneMapper.selectInfoByTitle(content);
    }

}
