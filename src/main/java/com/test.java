package com;

import com.util.TestData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class test {

    @Test (dataProvider = "Testdata1", dataProviderClass = TestData.class)
    @SuppressWarnings("unchecked")
    public void testhaha(HashMap<String, Object> hashMap){
        System.out.println(hashMap.get("No ") + "  " + hashMap.get("description") + "  " + hashMap.get("value") );
    }

}
