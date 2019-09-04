package com.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class InitDriver {

    private static DesiredCapabilities capabilities;
    private static AppiumDriver<?> driver;


    public static DesiredCapabilities getAndroidDriverCapacity(){

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("app", "/Users/finup/IdeaProjects/AppDemoAuto/app/Android/loan_customer_debug_v1.2.7.apk");
        capabilities.setCapability("appPackage", "com.puhuicredit.app");
        capabilities.setCapability("appActivity", "com.finupcredit.app.ui.activity.MainActivityGroup");
        capabilities.setCapability("unicodeKeyboard", true);
        return capabilities;
    }

    public static DesiredCapabilities getIOSCapacity(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("app", "/Users/finup/IdeaProjects/AppDemoAuto/app/Android/loan_customer_debug_v1.2.7.apk");
        capabilities.setCapability("appPackage", "com.puhuicredit.app");
        capabilities.setCapability("appActivity", "com.finupcredit.app.ui.activity.MainActivityGroup");
        capabilities.setCapability("unicodeKeyboard", true);
        return capabilities;
    }

    public static AppiumDriver initDriver(String platfrom) throws MalformedURLException {


        if(platfrom.equals("android")){
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), getAndroidDriverCapacity());
        } else {
            driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), getIOSCapacity());
        }

        return driver;
    }

    public static void quitDriver(){
        driver.quit();
    }

}
