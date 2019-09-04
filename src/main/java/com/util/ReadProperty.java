package com.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ReadProperty {

    public static String getStateCNValue(String key){
        String fileName = "elementMap.properties";
        String value = null;
        InputStreamReader inputStreamReader = null;
        Properties properties = new Properties();
        try {
            inputStreamReader = new InputStreamReader(ReadProperty.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
            properties.load(inputStreamReader);
            value = properties.getProperty(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return value != null ? value : null;

    }

    public static void main(String[] args) {
        System.out.println(ReadProperty.getStateCNValue("android.app"));
    }
}
