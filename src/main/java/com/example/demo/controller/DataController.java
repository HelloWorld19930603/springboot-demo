package com.example.demo.controller;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.service.AcclogViewService;
import com.example.demo.service.VisitorlogViewService;

import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultJson;
import com.example.demo.utils.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    @Autowired
    AcclogViewService acclogViewService;

    @Autowired
    VisitorlogViewService visitorlogViewService;

    @GetMapping("visit")
    public String index(){
        return "visit";
    }


    @RequestMapping("list")
    @ResponseBody
    public PageUtil list(int start,int pageSize){
        IPage p = visitorlogViewService.page(new Page(start,pageSize),null);
        PageUtil page = new PageUtil((int)p.getTotal(),p.getRecords());
        return page;
    }

    @RequestMapping("count")
    @ResponseBody
    public Map count( String startTime){
        Map map = new HashMap();
        map.put("startTime",startTime);
        List<Map<String,Object>> accMap = acclogViewService.selectAccCount(map);
        List<Map<String,Object>> visitorMap = visitorlogViewService.selectVisitorCount(map);
        map.put("num1",0);
        map.put("num2",0);
        map.put("num3",0);
        map.put("num4",0);
        map.put("num5",0);
        map.put("num6",0);
        for(Map m1 : accMap){
            if(m1.get("deptname").equals(0) && m1.get("n").equals(1)){
                map.put("num1",(int)map.get("num1") +1 );
            }else if(m1.get("deptname").equals(2)){
                map.put("num2",(int)map.get("num2") +1 );
            }else{
                map.put("num3",(int)map.get("num3") +1 );
            }
        }
        for(Map m2 : visitorMap){
            if(m2.get("visitor_type").equals("vip")){
                map.put("num4",(int)map.get("num4") +1 );
            }else if(m2.get("visitor_type").equals("供应商")){
                map.put("num5",(int)map.get("num5") +1 );
            }else if(m2.get("visitor_type").equals("TK临时")){
                map.put("num6",(int)map.get("num6") +1 );
            }
        }
        int all = (int)map.get("num1")+(int)map.get("num2")+(int)map.get("num3")+(int)map.get("num4")+(int)map.get("num5")+(int)map.get("num6");
        map.put("all",all);
        return map;
    }


    @RequestMapping("exportVisitor")
    public void exportVisitor(HttpServletRequest req, HttpServletResponse resp, String startDate, String endDate) {
        try {
            Map<String, String> dic = new HashMap();
            dic.put("visitorPin", "访客编号");
            dic.put("visitorName", "访客名字");
            dic.put("visitorMobile", "访客联系方式");
            dic.put("visitorCompany", "访客公司");
            dic.put("visitorPlate", "访客车牌号");
            dic.put("name", "被访人姓名");
            dic.put("pin", "被访人编号");
            dic.put("visitorStatu", "访客状态");
            dic.put("inAddress", "访客地址");
            dic.put("inTime", "进入时间");
            dic.put("outTime", "离开时间");
            dic.put("visitorType", "访客类型");
            OutputStream out = resp.getOutputStream();
            resp.setContentType("application/vnd.ms-excel;charset=utf-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + processFileName(req, "访客" + startDate + "_" + endDate + ".xls"));
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, Object>> list = visitorlogViewService.selectMap(sDateFormat.parse(startDate));
            WriteExcel.writeExcel(list, dic, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //解决设置名称时的乱码
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

}
