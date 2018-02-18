package cn.scene.serviceImpl;

import cn.scene.dao.JifenRecordMapper;
import cn.scene.model.JifenRecord;
import cn.scene.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员类
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private JifenRecordMapper jifenRecord;

    @Override
    public List<JifenRecord> selectJifen(Integer userId) {
        return jifenRecord.selectByUserId(userId);
    }
}
