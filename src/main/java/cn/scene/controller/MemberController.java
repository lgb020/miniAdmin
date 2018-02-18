package cn.scene.controller;

import cn.scene.model.JifenRecord;
import cn.scene.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    //兑换记录
    @RequestMapping("/j/records")
    public @ResponseBody List<JifenRecord> jiFen(HttpServletRequest request){
        String index = request.getParameter("userId");
        String regx = "^[0-9]+$";
        List<JifenRecord> records = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int userId = Integer.parseInt(index);
            records = memberService.selectJifen(userId);
        }
        return records;
    }

}
