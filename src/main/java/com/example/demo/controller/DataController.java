package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.service.AcclogViewService;
import com.example.demo.service.VisitorlogViewService;

import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    AcclogViewService acclogViewService;

    @Autowired
    VisitorlogViewService visitorlogViewService;

    @GetMapping("index")
    public String index(){
        return "index";
    }


    @RequestMapping("list")
    @ResponseBody
    public PageUtil list(int start,int pageSize){
        IPage p = visitorlogViewService.page(new Page(start,pageSize),null);
        PageUtil page = new PageUtil((int)p.getTotal(),p.getRecords());
        return page;
    }
}
