package com.example.demo.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.AcclogView;
import com.example.demo.service.AcclogViewService;
import com.example.demo.service.VisitorlogViewService;
import com.example.demo.service.impl.AcclogViewImpl;
import com.example.demo.utils.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleJob {

    @Autowired
    VisitorlogViewService visitorlogViewService;
    @Autowired
    AcclogViewService acclogViewService;

    public static void main(String[] args) throws IOException {

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date(new Date().getTime() - 36 * 3600000);
        long time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
        long start = current.getTime() - time + 2 * 3600000;
        long end = current.getTime() - time + 26 * 3600000;
        String startDate = sDateFormat.format(new Date(start));
        String startDate2 = sDateFormat2.format(new Date(start));
        String endDate = sDateFormat.format(new Date(end));
        String endDate2 = sDateFormat2.format(new Date(end));
        Map<String, String> dic2 = new LinkedHashMap();
        dic2.put("name", "员工名字");
        dic2.put("mobile", "手机号码");
        dic2.put("deptname", "部门");
        dic2.put("reader_name", "读卡器名称");
        dic2.put("device_name", "设备名称");
        dic2.put("pin", "人员编号");
        dic2.put("sn", "sn");
        dic2.put("time", "刷卡时间");
        dic2.put("event_type", "事件类型");
        OutputStream out2 = new FileOutputStream("d://报表/门禁报表1_" + startDate2 + "_" + endDate2 + ".xls");
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map map1 = new HashMap();
        map1.put("pin","1164");
        map1.put("time","2019-09-26 05:18:34");
        Map map2 = new HashMap();
        map2.put("pin","1164");
        map2.put("time","2019-09-26 05:19:59");
        list2.add(map1);
        list2.add(map2);
        AcclogViewService acclogViewService = new AcclogViewImpl();
        WriteExcel.writeExcel(acclogViewService.removeRepeatAcc(list2), dic2, out2);
    }

    //添加定时任务
    @Scheduled(cron = "0 0 2 * * ?")
    public void configureTasks() throws IOException, ParseException {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        File file = new File("d://报表");
        if (!file.exists()) {
            file.mkdir();
        }
        Map<String, String> dic = new LinkedHashMap<>();
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

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date(new Date().getTime() - 26 * 3600000);
        long time = current.getHours() * 3600000 + current.getMinutes() * 60000 + current.getSeconds() * 1000;
        long start = current.getTime() - time + 2 * 3600000;
        long end = current.getTime() - time + 26 * 3600000;
        String startDate = sDateFormat.format(new Date(start));
        String startDate2 = sDateFormat2.format(new Date(start));
        String endDate = sDateFormat.format(new Date(end));
        String endDate2 = sDateFormat2.format(new Date(end));
        OutputStream out1 = new FileOutputStream("d://报表/所有访客_" + startDate2 + "_" + endDate2 + ".xls");
        List<Map<String, Object>> list1 = visitorlogViewService.selectMap(startDate, endDate, "所有访客", null);
        WriteExcel.writeExcel(list1, dic, out1);


        Map<String, String> dic2 = new LinkedHashMap();
        dic2.put("name", "员工名字");
        dic2.put("mobile", "手机号码");
        dic2.put("deptname", "部门");
        dic2.put("reader_name", "读卡器名称");
        dic2.put("device_name", "设备名称");
        dic2.put("pin", "人员编号");
        dic2.put("sn", "sn");
        dic2.put("time", "刷卡时间");
        dic2.put("event_type", "事件类型");
        OutputStream out2 = new FileOutputStream("d://报表/门禁报表1_" + startDate2 + "_" + endDate2 + ".xls");
        List<Map<String, Object>> list2 = acclogViewService
                .listMaps(new QueryWrapper<AcclogView>().lambda()
                        .ge(AcclogView::getTime, startDate)
                        .le(AcclogView::getTime, endDate)
                        .eq(AcclogView::getEventType, "正常验证开门")
                        .isNotNull(AcclogView::getName));
        WriteExcel.writeExcel(acclogViewService.removeRepeatAcc(list2), dic2, out2);


        Map<String, String> dic3 = new LinkedHashMap<>();
        dic3.put("name", "员工名字");
        dic3.put("pin", "员工编号");
        dic3.put("mobile", "手机号码");
        dic3.put("deptname", "部门");
        dic3.put("type", "类别");
        dic3.put("s", "状态");
        dic3.put("n", "ID2");

        OutputStream out3 = new FileOutputStream("d://报表/门禁报表2_" + startDate2 + "_" + endDate2 + ".xls");
        List<Map<String, Object>> list = acclogViewService
                .listMaps(new QueryWrapper<AcclogView>().lambda()
                        .ge(AcclogView::getTime, startDate != null ? sDateFormat.parse(startDate) : null)
                        .le(AcclogView::getTime, endDate != null ? sDateFormat.parse(endDate) : null)
                        .eq(AcclogView::getEventType, "正常验证开门")
                        .isNotNull(AcclogView::getName));
        List<String> times = acclogViewService.getRepeatAcc(list1);
        List<Map<String, Object>> list3 = acclogViewService.selectMap(startDate, endDate, times);
        WriteExcel.writeExcel(list3, dic3, out3);
    }


}
