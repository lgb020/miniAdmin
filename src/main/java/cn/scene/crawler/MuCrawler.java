package cn.scene.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.scene.util.MusicSave;

/**
 * 爬取包图网上的背景音乐素材
 */
public class MuCrawler extends BreadthCrawler{

    //构造方法设置抓取规则
    public MuCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        //添加种子页面
        this.addSeed("http://ibaotu.com/peiyue/11-0-0-0-0-1.html");
        //设置采集规则的类型网页
        this.addRegex("http://ibaotu.com/peiyue/11-[0-9]+-[0-9]+-[0-9]+-[0-9]+-[0-9]+\\.html");
        //不匹配图片
        this.addRegex("-.*\\.(jpg|png|gif).*");
        //不匹配#**的链接
        this.addRegex("-.*#.*");
        //设置线程数
        this.setThreads(10);
        //停止后下次继续爬取
        this.setResumable(true);
        //设置线程之间的等待时间
        getConf().setExecuteInterval(1000);
        //爬取URL上限
        getConf().setTopN(100000);
    }

    //抓取和解析页面数据
    public void visit(Page page, CrawlDatums next){
        //正则表达式匹配链接
        if(page.matchUrl("http://ibaotu.com/peiyue/11-[0-9]+-[0-9]+-[0-9]+-[0-9]+-[0-9]+\\.html")) {
            String name = page.select("div[class=audio-info]>a").first().text();
            String url = page.select("audio>source").attr("src");
            String length = page.select("span[class=end-time]").first().text();
            String info = name+","+url+","+length+"\r\n";
            try{
                //信息输出到本地文件
                MusicSave.out(info);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
