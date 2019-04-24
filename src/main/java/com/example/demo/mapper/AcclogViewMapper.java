package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AcclogView;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cc
 * @since 2019-04-23
 */
public interface AcclogViewMapper extends BaseMapper<AcclogView> {


    public List<Map<String,Object>> selectAccCount(Map map);
}
