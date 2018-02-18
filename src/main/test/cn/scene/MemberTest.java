package cn.scene;

import cn.scene.model.JifenRecord;
import cn.scene.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员测试
 */
public class MemberTest extends BaseJunit4Test{

    @Autowired
    private MemberService memberService;

    @Test
    public void jifen(){
        String index = "1";
        String regx = "^[0-9]+$";
        List<JifenRecord> records = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)){
            int userId = Integer.parseInt(index);
            records = memberService.selectJifen(userId);
        }
        System.out.println(records);
    }
}
