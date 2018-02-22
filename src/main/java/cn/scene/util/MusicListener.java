package cn.scene.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 监听爬取到的数据保存到数据库
 */
public class MusicListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        /*每天凌晨2：00：00执行,若超过时间，当天不再执行，等到明天再执行*/
        calendar.set(year,month,day,2,00,00);
        Date defaultdate = calendar.getTime();
        Date sendDate = new Date();
        if (defaultdate.before(sendDate)) {
            // 将发送时间设为明天5点
            calendar.add(Calendar.DATE, 1);
            sendDate = calendar.getTime();
        }

        //每月1号爬取数据
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 每天执行，若为每月1号凌晨2点-3点开始插入数据
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (day == 1 && hour>=2 && hour<3) {
                    try{
                        MusicSave.insert();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }, sendDate, 24 * 60 * 60 * 1000);// 每天执行一次检查
    }

}
