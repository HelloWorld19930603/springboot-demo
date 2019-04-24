package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.VisitorlogView;

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
public interface VisitorlogViewMapper extends BaseMapper<VisitorlogView> {

    public List<Map<String,Object>> selectMap(Map map);

    public List<Map<String,Object>> selectVisitorCount(Map map);
}
