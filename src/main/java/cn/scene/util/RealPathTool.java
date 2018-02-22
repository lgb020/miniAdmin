package cn.scene.util;

import java.io.File;

/**
 * 获取项目在服务器上的真实路径
 */
public class RealPathTool {
    public static String classPath = RealPathTool.class.getClassLoader().getResource("/").getPath();

    public static String getRootPath() {
        String rootPath = "";
        //windows下
        if("\\".equals(File.separator)){
            rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("/", "\\");
        }
        //linux下
        if("/".equals(File.separator)){
            rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
            rootPath = rootPath.replace("\\", "/");
        }
        return rootPath;
    }
}
