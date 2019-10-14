package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.AcclogView;
import com.example.demo.mapper.AcclogViewMapper;
import com.example.demo.service.AcclogViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Map<String, Object>> selectMap(String startTime, String endTime, List<String> times) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        if(times !=null && times.size() > 0){
            String timeStr = "";
            for(String str : times){
                timeStr += "'" + str+"',";
            }
            map.put("times",timeStr.substring(0,timeStr.length() -1));
        }
        return acclogViewMapper.selectMap(map);
    }

    @Override
    public List<Map<String, Object>> removeRepeatAcc(List<Map<String, Object>> list) {
        List<Map<String, Object>> result = new ArrayList();
        Map<Object, Map> map =new HashMap();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (int i = 0; i < list.size() - 1 ; i++) {
                Map<String, Object> map1 = list.get(i);
                Map<String, Object> map2 = list.get(i + 1);
                if(map1.get("pin").equals(map2.get("pin"))){
                    Date date1  = sDateFormat.parse(map1.get("time").toString());
                    Date date2 = sDateFormat.parse(map2.get("time").toString());
                    if(date2.getTime() - date1.getTime() < 60000){
                        i += 1;
                    }
                }
                if(map.get(map1.get("pin")) != null){
                    Date date1  = sDateFormat.parse(map.get(map1.get("pin")).get("time").toString());
                    Date date2 = sDateFormat.parse(map1.get("time").toString());
                    map.put(map1.get("pin"),map1);
                    if(date2.getTime() - date1.getTime() < 60000){
                        continue;
                    }
                }else{
                    map.put(map1.get("pin"),map1);
                }
                result.add(map1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getRepeatAcc(List<Map<String, Object>> list) {
        List<String> result = new ArrayList();
        Map<Object, Map> map =new HashMap();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (int i = 0; i < list.size() - 1 ; i++) {
                Map<String, Object> map1 = list.get(i);
                Map<String, Object> map2 = list.get(i + 1);
                if(map1.get("pin").equals(map2.get("pin"))){
                    Date date1  = sDateFormat.parse(map1.get("time").toString());
                    Date date2 = sDateFormat.parse(map2.get("time").toString());
                    if(date2.getTime() - date1.getTime() < 60000){
                        i += 1;
                        result.add(map2.get("time").toString());
                    }
                }
                if(map.get(map1.get("pin")) != null){
                    Date date1  = sDateFormat.parse(map.get(map1.get("pin")).get("time").toString());
                    Date date2 = sDateFormat.parse(map1.get("time").toString());
                    map.put(map1.get("pin"),map1);
                    if(date2.getTime() - date1.getTime() < 60000){
                        result.add(map1.get("time").toString());
                        continue;
                    }
                }else{
                    map.put(map1.get("pin"),map1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
