package cn.scene;

import cn.scene.model.JifenRecord;
import cn.scene.service.JifenRecordService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分测试
 */
public class JifenTest extends BaseJunit4Test{

    @Autowired
    private JifenRecordService jifenRecordService;

    @Test
    public void jifenByType(){
        String index = "2";
        String regx = "^[0-9]+$";
        List<JifenRecord> list = new ArrayList<>();
        if(StringUtils.isNotBlank(index) && index.matches(regx)) {
            int temp = Integer.parseInt(index);
            list = jifenRecordService.selectJifen(1,temp);
        }
        System.out.println(list);
    }
}
