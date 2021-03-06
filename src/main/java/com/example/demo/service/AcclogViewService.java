package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.AcclogView;

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
public interface AcclogViewService extends IService<AcclogView> {

    public List<Map<String,Object>> selectAccCount(Map map);

    List<Map<String, Object>> selectMap(String startTime, String endTime, List<String> times);

    public List<Map<String, Object>> removeRepeatAcc(List<Map<String, Object>> list);

    List<String> getRepeatAcc(List<Map<String, Object>> list);
}
