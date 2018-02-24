package cn.scene;

import cn.scene.controller.MuController;
import cn.scene.crawler.MuCrawler;
import cn.scene.model.Music;
import cn.scene.service.MuService;
import cn.scene.util.DateFormat;
import cn.scene.util.MusicSave;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 音乐测试
 */
public class musicTest extends BaseJunit4Test{

    @Autowired
    private MuService muService;

    @Test
    public void musicList(){
        String index = "2";
        String regx = "^[0-9]+$";
        List<Music> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int page = Integer.parseInt(index);
            list = muService.list(page);
        }
        System.out.println(list);
    }

    @Test
    public void search(){
        String content = "企业";
        List<Music> list = new ArrayList<>();
        if(StringUtils.isNotBlank(content)){
            list = muService.search(content);
        }
        System.out.println(list);
    }

    @Test
    public void insert() throws Exception{
        MusicSave.insert();
    }

    @Test
    public void allPage(){
        int page = muService.allPage();
        System.out.println(page);
    }
}
