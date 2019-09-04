package com.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private String fileName;
    private String sheetName;
    private String filePath;
    private HSSFWorkbook workbook = null;

    public ExcelUtil(String fileName, String filePath, String sheetName){
        this.fileName = fileName;
        this.sheetName = sheetName;
        this.filePath = filePath + fileName;
    }

    public Object[][] readExcel() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
        //获得行数。
        int rowNum = sheet.getPhysicalNumberOfRows();
        //声明HashMap二维数组，根据表单行数创建对应的map对象
        @SuppressWarnings("unchecked")
        HashMap<String,Object>[][] arrayMap = new HashMap[rowNum - 1][1];
        for(int i = 1 ; i< rowNum; i++ ){
            arrayMap[i-1][0] = new HashMap<String, Object>();
        }
        //获取标题行数据
        List<String> arraylist = new ArrayList();
        Row rowTitle = sheet.getRow(0);
        for(int i=0; i<rowTitle.getPhysicalNumberOfCells(); i++){
            Cell cell = rowTitle.getCell(i);
            arraylist.add(i, cell.getStringCellValue());
        }
        for(int i=1; i<rowNum;i++){
            Row r = sheet.getRow(i);
            for(int j=0; j<r.getPhysicalNumberOfCells(); j++){
                Cell cell = r.getCell(j);
                String cellValue = cell.getStringCellValue();
                arrayMap[i-1][0].put(arraylist.get(j),cellValue);
            }
        }
        return arrayMap;
    }

//    public static void main(String[] args) throws Exception {
//
//        String workPath = System.getProperty("user.dir");
//        String filepath = workPath + "/Data/";
//        ExcelUtil excelUtil = new ExcelUtil("testData.xls",filepath,"test1");
//        Object[][] hashMap = excelUtil.readExcel();
//        for(int i=0;i <hashMap.length;i++){
//            HashMap<String, Object> data = (HashMap<String, Object>) hashMap[i][0];
//            System.out.println(data);
//        }
//        try {
//            excelUtil.readExcel();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
