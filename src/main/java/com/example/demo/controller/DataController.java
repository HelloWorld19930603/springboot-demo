package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.AcclogView;
import com.example.demo.entity.VisitorlogView;
import com.example.demo.schedule.ScheduleJob;
import com.example.demo.service.AcclogViewService;
import com.example.demo.service.VisitorlogViewService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.StringUtil;
import com.example.demo.utils.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DataController {

    @Autowired
    AcclogViewService acclogViewService;

    @Autowired
    VisitorlogViewService visitorlogViewService;

    @Autowired
    ScheduleJob scheduleJob;

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

    @GetMapping("visit")
    public String index() {
        return "visit";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageUtil list(int start, int pageSize, String name, String mobile, String plate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        String startTime = simpleDateFormat.format(new Date(new Date().getTime() - 24L * 3600000));
        IPage p = visitorlogViewService.page(new Page(start, pageSize), new QueryWrapper<VisitorlogView>()
                .lambda().ge(VisitorlogView::getInTime, startTime)
                .like(StringUtil.isNotEmpty(name), VisitorlogView::getName, name)
                .like(StringUtil.isNotEmpty(mobile), VisitorlogView::getVisitorMobile, mobile)
                .like(StringUtil.isNotEmpty(plate), VisitorlogView::getVisitorPlate, plate));
        PageUtil page = new PageUtil((int) p.getTotal(), p.getRecords());
        return page;
    }

    @RequestMapping("count")
    @ResponseBody
    public Map count(String startTime) {
        Map map = new HashMap();
        map.put("startTime", startTime);
        List<Map<String, Object>> accMap = acclogViewService.selectAccCount(map);
        List<Map<String, Object>> visitorMap = visitorlogViewService.selectVisitorCount(map);
        map.put("num1", 0);//内部员工
        map.put("num2", 0);//TK分公司人员
        map.put("num3", 0);//异常人员
        map.put("num4", 0); //客户
        map.put("num5", 0);//临时供应商
        map.put("num6", 0);//临时来访
        map.put("num7", 0);//长期供应商
        for (Map m1 : accMap) {
            if (m1.get("deptname").equals(0) && m1.get("n").equals(1)) {
                map.put("num1", (int) map.get("num1") + 1);
            } else if (m1.get("deptname").equals(1) && m1.get("n").equals(1)) {
                map.put("num7", (int) map.get("num7") + 1);
            } else if (!m1.get("n").equals(1) || !m1.get("n").equals(0)) {
                map.put("num3", (int) map.get("num3") + 1);
            }
        }
        for (Map m2 : visitorMap) {
            if (StringUtil.isEqual(m2.get("visitor_type"), "客户")) {
                map.put("num4", (int) map.get("num4") + 1);
            } else if (StringUtil.isEqual(m2.get("visitor_type"), "供应商")) {
                map.put("num5", (int) map.get("num5") + 1);
            } else if (StringUtil.isEqual(m2.get("visitor_type"), "访客")) {
                map.put("num6", (int) map.get("num6") + 1);
            } else if (StringUtil.isEqual(m2.get("visitor_type"), "内部人员（分公司）")) {
                map.put("num2", (int) map.get("num2") + 1);
            }
        }
        int all = (int) map.get("num1") + (int) map.get("num2") + (int) map.get("num3")
                + (int) map.get("num4") + (int) map.get("num5") + (int) map.get("num7");
        map.put("all", all);
        return map;
    }

    @RequestMapping("exportVisitor")
    public void exportVisitor(HttpServletRequest req, HttpServletResponse resp, String startDate, String endDate, String type) {
        try {
            Map<String, String> dic = new LinkedHashMap();
            // dic.put("visitorPin", "访客编号");
            dic.put("visitorName", "访客名字");
            dic.put("visitorMobile", "访客联系方式");
            dic.put("visitorCompany", "访客公司");
            dic.put("visitorPlate", "访客车牌号");
            dic.put("name", "被访人姓名");
            dic.put("visitorType", "访客类型");
            // dic.put("pin", "被访人编号");
            // dic.put("visitorStatu", "访客状态");
            dic.put("s", "访客状态");
            dic.put("inAddress", "访客地址");
            dic.put("inTime", "进入时间");
            dic.put("outTime", "离开时间");

            OutputStream out = resp.getOutputStream();
            resp.setContentType("application/vnd.ms-excel;charset=utf-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + processFileName(req, type + startDate + "_" + endDate + ".xls"));
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, Object>> list = visitorlogViewService.selectMap(startDate != null ? sDateFormat.parse(startDate) : null,null,type, null);
            WriteExcel.writeExcel(list, dic, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("exportAcc")
    public void exportAcc(HttpServletRequest req, HttpServletResponse resp, String startDate, String endDate) {
        try {
            Map<String, String> dic = new LinkedHashMap();
            dic.put("name", "员工名字");
            dic.put("mobile", "手机号码");
            dic.put("deptname", "部门");
            dic.put("reader_name", "读卡器名称");
            dic.put("device_name", "设备名称");
            dic.put("pin", "人员编号");
            dic.put("sn", "sn");
            dic.put("time", "刷卡时间");
            dic.put("event_type", "事件类型");
            OutputStream out = resp.getOutputStream();
            resp.setContentType("application/vnd.ms-excel;charset=utf-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + processFileName(req, "门禁报表1—" + startDate + "_" + endDate + ".xls"));
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, Object>> list = acclogViewService
                    .listMaps(new QueryWrapper<AcclogView>().lambda()
                            .ge(AcclogView::getTime, startDate != null ? sDateFormat.parse(startDate) : null)
                            .le(AcclogView::getTime, endDate != null ? sDateFormat.parse(endDate) : null)
                            .eq(AcclogView::getEventType, "正常验证开门")
                            .isNotNull(AcclogView::getName));
            WriteExcel.writeExcel(acclogViewService.removeRepeatAcc(list), dic, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }

    @RequestMapping("exportAcc2")
    public void exportAcc2(HttpServletRequest req, HttpServletResponse resp, String startDate, String endDate) {
        try {
            Map<String, String> dic = new LinkedHashMap();
            dic.put("name", "员工名字");
            dic.put("pin", "员工编号");
            dic.put("mobile", "手机号码");
            dic.put("deptname", "部门");
            dic.put("type", "类别");
            dic.put("s", "状态");
            dic.put("n", "ID2");
            OutputStream out = resp.getOutputStream();
            resp.setContentType("application/vnd.ms-excel;charset=utf-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + processFileName(req, "门禁报表2—" + startDate + "_" + endDate + ".xls"));
            List<Map<String, Object>> list = acclogViewService.selectMap(startDate,endDate);
            WriteExcel.writeExcel(list, dic, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("test")
    public void test() throws IOException {
        System.out.println("test");
        scheduleJob.configureTasks();
    }
}
