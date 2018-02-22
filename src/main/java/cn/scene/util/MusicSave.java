package cn.scene.util;

import cn.scene.service.MuService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.*;
import java.util.Date;

public class MusicSave {

    //抓取得到的数据保存到txt文件
    public static void out(String info) throws Exception{
        String name = DateFormat.format(new Date()); //根据时间命名文件
        String path = RealPathTool.getRootPath()+"/music/"+name+".txt";
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        Writer write = new FileWriter(file,true);
        write.write(info);
        write.close();
    }

    //读取txt文件数据保存到数据库中
    public static void insert() throws Exception{
        String fileName = DateFormat.format(new Date()); //根据时间命名文件
        String path = RealPathTool.getRootPath()+"/music/"+fileName+".txt";
        File file = new File(path);
        if(file.exists()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
            MuService muService = (MuService) context.getBean("muService");
            String info = null;
            //一次读取一行
            while((info = br.readLine())!=null){
                String array[] = info.split(",");
                String name = array[0];
                String url = array[1];
                String length = array[2];
                muService.insert(name,url,length);
            }
        }
    }
}
