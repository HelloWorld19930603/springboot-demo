package com.example.demo.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExportWordUtil {

    public  static void main(String [] s) {
        Map dataMap = new HashMap();
        dataMap.put("name", "张三");
        dataMap.put("sex", "男");
        dataMap.put("birthday", "2009-1-1");
        dataMap.put("preschool", "第一幼儿园");
        dataMap.put("spciality", "唱歌、跳舞");
        dataMap.put("hukou_address", "东城区西直门南礼士路北口");
        dataMap.put("address", "西城区东四十条");
        dataMap.put("father_name", "张学良");
        dataMap.put("father_political", "国民党党员");
        dataMap.put("father_education", "本科");
        dataMap.put("father_organization", "军阀");
        dataMap.put("father_phone", "13111111111");
        dataMap.put("mother_name", "赵四");
        dataMap.put("mother_political", "无党派");
        dataMap.put("mother_education", "私塾");
        dataMap.put("mother_organization", "家庭主妇");
        dataMap.put("mother_phone", "13111111112");
        dataMap.put("wish_flag", "是");
        dataMap.put("nowish_flag", "https://img-blog.csdnimg.cn/20190617140355217.jpg");

        // 输出文档路径及名称
        File outFile = new File("D:/test3.doc");
        OutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
            exportWord(dataMap, out,"a.ftl");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            out.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportWord(Map<String, Object> map, OutputStream out, String name) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        String templatePath = ExportWordUtil.class.getResource("/").getPath()+ "/ftl";
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Template tmpl = cfg.getTemplate(name);
        Writer writer = new OutputStreamWriter(out, "utf-8");
        tmpl.process(map, writer);
        writer.flush();
        writer.close();
    }

    public static String getTemplate(String template, Map<String,Object> map) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        String templatePath = ExportWordUtil.class.getResource("/").getPath()+"/templates";
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        return stringWriter.toString();
    }

}
