package cn.scene.serviceImpl;

import cn.scene.dao.AdvertiseMapper;
import cn.scene.model.Advertise;
import cn.scene.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图实现类
 */
@Service("advService")
public class AdvServiceImpl implements AdvService{

    @Autowired
    private AdvertiseMapper advertiseMapper;

    public List<Advertise> info() {
        return advertiseMapper.selectInfo();
    }
}
