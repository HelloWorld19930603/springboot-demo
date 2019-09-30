package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.VisitorlogView;
import com.example.demo.mapper.VisitorlogViewMapper;
import com.example.demo.service.VisitorlogViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2019-04-23
 */
@Service
@Transactional
public class VisitorlogViewImpl extends ServiceImpl<VisitorlogViewMapper, VisitorlogView> implements VisitorlogViewService {

    @Resource
    VisitorlogViewMapper visitorlogViewMapper;

    @Override
    public List<Map<String, Object>> selectMap(Object startTime,Object endTime, String type, String outTime) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("type",type);
        map.put("outTime",outTime);
        return visitorlogViewMapper.selectMap(map);
    }

    @Override
    public int count(Object startTime) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        return visitorlogViewMapper.count(map);
    }

    @Override
    public List<Map<String,Object>> selectVisitorCount(Map map) {
        return visitorlogViewMapper.selectVisitorCount(map);
    }
}
