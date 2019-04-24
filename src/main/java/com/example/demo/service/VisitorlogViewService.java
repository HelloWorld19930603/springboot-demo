package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.VisitorlogView;

import java.util.Date;
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

    public List<Map<String,Object>> selectMap(Date startTime);

    public List<Map<String,Object>> selectVisitorCount(Map map);
}
