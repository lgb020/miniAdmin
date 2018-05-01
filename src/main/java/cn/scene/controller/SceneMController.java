package cn.scene.controller;

import cn.scene.model.DataDetail;
import cn.scene.model.Scene;
import cn.scene.model.User;
import cn.scene.service.SceneMService;
import cn.scene.service.SceneService;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 场景管理:上架，编辑，设置，删除
 */
@Controller
@RequestMapping("/s/manage")
public class SceneMController {

    @Autowired
    private SceneMService sceneMService;
    @Autowired
    private SceneService sceneService;

    //上架
    @RequestMapping("/shelve")
    public @ResponseBody Boolean shelve(HttpServletRequest request){
        String index = request.getParameter("id");
        String jInden = request.getParameter("jifen");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(index.matches(regx) && jInden.matches(regx)){
            int id = Integer.parseInt(index);
            int jifen = Integer.parseInt(jInden);
            result = sceneMService.shelve(id,jifen);
        }
        return result;
    }

    //设置
    @RequestMapping("/setting")
    public @ResponseBody Scene setting(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            Scene scene = sceneMService.setting(id);
            return scene;
        }
        return null;
    }

    //完成设置
    @RequestMapping("/issue")
    public @ResponseBody Boolean issue(HttpServletRequest request,Scene scene){
        if(scene!=null){
            int temp = sceneMService.issue(scene);
            if(temp>0){
                return true;
            }
        }
        return false;
    }

    //模板删除
    @RequestMapping("/del")
    public @ResponseBody Boolean delete(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        Boolean result = false;
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            result = sceneMService.delete(id);
        }
        return result;
    }

    //场景下架
    @RequestMapping("/down")
    public @ResponseBody int downScene(HttpServletRequest request){
        String index = request.getParameter("id");
        String regx = "^[0-9]+$";
        int result = 0;
        if(index.matches(regx)){
            int id = Integer.parseInt(index);
            result = sceneMService.updateIssue(id);
        }
        return result;
    }

    //场景举报ip查询
    @RequestMapping("/ip")
    public @ResponseBody Boolean ipSelect(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String userIp = request.getParameter("ip"); //用户客户端ip
        String regx = "^[0-9]+$";
        String ipRegx = "^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$";
        if(index.matches(regx) && userIp.matches(ipRegx)){
            int sceneId = Integer.parseInt(index);
            return sceneMService.ipIsExit(sceneId,userIp);
        }
        return false;
    }

    //场景举报
    @RequestMapping("/report")
    public @ResponseBody int reportScene(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String userIp = request.getParameter("ip"); //用户客户端ip
        String content = request.getParameter("content");
        String regx = "^[0-9]+$";
        String ipRegx = "^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$";
        //正则表达式验证信息
        if(index.matches(regx) && userIp.matches(ipRegx)
                && StringUtils.isNotBlank(content)){
            int sceneId = Integer.parseInt(index);
            return sceneMService.reportScene(sceneId,userIp,content);
        }
        return 0;
    }

    //场景兑换
    @RequestMapping("/exchange")
    @Transactional
    public @ResponseBody int exchangeScene(HttpServletRequest request){
        String index = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index);
            //查询场景信息
            Scene scene = sceneService.scene(sceneId);
            //更新场景信息为作者信息
            User user = (User)request.getSession().getAttribute("user");
            String code = UUID.randomUUID().toString().substring(0,8); //生成访问码
            scene.setFromScene(scene.getUserId()); //更新父场景
            scene.setUserId(user.getId());
            scene.setCode(code);
            return sceneMService.exchangeScene(scene);
        }
        return 0;
    }

    //数据收集
    @RequestMapping("/collection")
    public @ResponseBody int collection(HttpServletRequest request,DataDetail date){
        return sceneMService.saveDateDetail(date);
    }

    //数据导出
    @RequestMapping("/export")
    public @ResponseBody void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("sceneId");
        String regx = "^[0-9]+$";
        if(index.matches(regx)){
            int sceneId = Integer.parseInt(index); //场景id
            response.reset(); //清除缓存
            Map<String,Object> map=new HashMap<String,Object>();
            // 指定下载的文件名
            response.setHeader("Content-Disposition", "attachment;filename=活动人员情况.xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook= new XSSFWorkbook();
            //Excel对象
            workbook = sceneMService.exportExcelInfo(sceneId);
            //导出Excel
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        }
    }
}
