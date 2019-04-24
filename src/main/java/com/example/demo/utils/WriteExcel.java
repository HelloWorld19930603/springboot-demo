package com.example.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) {

        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        //writeExcel(list, 3, "D:/writeExcel.xlsx");
        System.out.println(System.currentTimeMillis());

    }


    /**
     * 去写Excel的方法
     * @param dataList  需要写的数据集合
     * @param dic  属性栏
     */
    public static void writeExcel(List<Map<String, Object>> dataList, Map<String, String> dic, OutputStream out){
        try {
            Workbook workBook = new HSSFWorkbook();
            // sheet 对应一个工作页
            Sheet sheet = workBook.createSheet();
            /**
             * 往Excel中写新数据
             */
            Row row = sheet.createRow(0);
            Object[] keys = dic.keySet().toArray();
            for (int k = 0; k < keys.length; k++) {
                Cell cell = row.createCell(k);
                cell.setCellValue(dic.get(keys[k]));
            }

            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                for (int k = 0; k < keys.length; k++) {
                    // 在一行内循环
                    Cell cell = row.createCell(k);
                    cell.setCellValue(StringUtil.toString(dataMap.get(keys[k])));
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}