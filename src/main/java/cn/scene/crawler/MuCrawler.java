package cn.scene.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.scene.service.MuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 爬取包图网上的背景音乐素材
 */
public class MuCrawler extends BreadthCrawler{

    public MuCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        //添加种子页面
        this.addSeed("http://ibaotu.com/peiyue/11-0-0-0-0-1.html");
        //设置采集规则的类型网页
        this.addRegex("http://ibaotu.com/peiyue/11-[0-9]+-[0-9]+-[0-9]+-[0-9]+-[0-9]+\\.html");
        //设置线程数
        this.setThreads(5);
        //不匹配图片
        this.addRegex("-.*\\.(jpg|png|gif).*");
        //线程之间的时间间隔
        getConf().setExecuteInterval(5000);
        //爬取URL上限
        getConf().setTopN(200000);
    }

    public void visit(Page page, CrawlDatums next){
        if(page.matchUrl("http://ibaotu.com/peiyue/11-[0-9]+-[0-9]+-[0-9]+-[0-9]+-[0-9]+\\.html")) {
            String name = page.select("div[class=audio-info]>a").first().text();
            String url = page.select("audio>source").attr("src");
            String length = page.select("span[class=end-time]").first().text();
            if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(length)){
                ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
                MuService muService = (MuService)context.getBean("muService");
                muService.insert(name,url,length);
            }
        }
    }
}
