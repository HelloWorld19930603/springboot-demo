package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.AcclogView;
import com.example.demo.mapper.AcclogViewMapper;
import com.example.demo.service.AcclogViewService;
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
public class AcclogViewImpl extends ServiceImpl<AcclogViewMapper, AcclogView> implements AcclogViewService {

    @Resource
    public AcclogViewMapper acclogViewMapper;

    @Override
    public List<Map<String,Object>> selectAccCount(Map map) {
        return acclogViewMapper.selectAccCount(map);
    }

    @Override
    public List<Map<String, Object>> selectMap(String startTime) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        return acclogViewMapper.selectMap(map);
    }
}
