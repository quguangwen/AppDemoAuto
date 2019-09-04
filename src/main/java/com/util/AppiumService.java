package com.util;

import java.io.IOException;

public class AppiumService {

    public static Process  process;
    public static String startAppiumService;


    public static void startAppium()  {
        startAppiumService = "appium --chromedriver-executable /Users/finup/IdeaProjects/AppDemoAuto/chromedriver/chromedriver";
        try {
            process = Runtime.getRuntime().exec(startAppiumService);
        } catch (IOException e) {
            try {
                process = Runtime.getRuntime().exec("pgrep node*");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void closeAppiumService(){
        process.destroy();
    }

    public static void main(String[] args) {

        AppiumService.startAppium();


    }
}
