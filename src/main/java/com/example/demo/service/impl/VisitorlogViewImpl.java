package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.VisitorlogView;
import com.example.demo.mapper.VisitorlogViewMapper;
import com.example.demo.service.VisitorlogViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
