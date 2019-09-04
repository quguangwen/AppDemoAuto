package com.util;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name="Testdata1")
    public static Object[][] createTestData(){
        String workPath = System.getProperty("user.dir");
        String filepath = workPath + "/Data/";
        try{
            System.out.println("filepath: " + filepath);
            ExcelUtil excelUtil = new ExcelUtil("testData.xls",filepath,"test1");
            return excelUtil.readExcel();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
