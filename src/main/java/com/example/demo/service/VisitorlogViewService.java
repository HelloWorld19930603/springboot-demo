package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.VisitorlogView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cc
 * @since 2019-04-23
 */
public interface VisitorlogViewService extends IService<VisitorlogView> {


    List<Map<String, Object>> selectMap(Object startTime, String type, String outTime);

    int count(Object startTime);

     List<Map<String,Object>> selectVisitorCount(Map map);
}
