package cn.scene;

import cn.scene.model.Sysfile;
import cn.scene.model.User;
import cn.scene.service.FileService;
import cn.scene.util.RealPathTool;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取
 */
public class FileTest extends BaseJunit4Test{

    @Autowired
    private FileService fileService;

    //遍历读取文件夹下的背景图
    @Test
    public void sysbg(){
        List<String> list = new ArrayList<>();
        String strPath = "C:\\Users\\hong\\Desktop\\sys";
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png")|| fileName.endsWith(".gif")) {
                    list.add(fileName);
                } else {
                    continue;
                }
            }

        }
        for(int i=0;i<list.size();i++){
            int result = fileService.addSysInfo("1","http://www.hsfeng.cn/scene/upload/img/sys/"+list.get(i));
            System.out.println(result);
        }
    }

    @Test
    public void fileInfo(){
        String index = "1";
        String type = "0";
        String regx = "^[0-9]+$";
        String tRegx = "^0|1$";
        List<Sysfile> info = new ArrayList<>();
        if(index.matches(regx) && type.matches(tRegx)){
            int page = Integer.parseInt(index);
            info = fileService.info(type,page);
        }
        System.out.println(info);
    }

    @Test
    public void count(){
        String type = "0";
        String regx = "^0|1$";
        int counts = 0;
        if(type.matches(regx)){
            counts = fileService.infoCount(type);
        }
        System.out.println(counts);
    }

}
