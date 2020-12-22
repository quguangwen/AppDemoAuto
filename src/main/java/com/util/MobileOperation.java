package com.util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;

public class MobileOperation {

    private AppiumDriver<?> driver;
    private WebDriver webDriver;




    public boolean waitForElement(String elementID, int timeOutSeconds){
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver,timeOutSeconds);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementID)));
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public boolean waitForElementTobeClickable(String element){
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver,5);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean waitForWebElementXpath(String element, int timeOutSeconds){
        try{
            this.webDriver = driver;
            WebDriverWait webDriverWait = new WebDriverWait(webDriver,timeOutSeconds);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void clickElementByID(String element){
        if(waitForElement(element,5)){
            driver.findElementById(element).click();
        } else {
            System.out.println(element + " element is not present");
        }
    }

    public void clickWebElementXpath(String element){
        if(waitForWebElementXpath(element,5)){
            webDriver.findElement(By.xpath(element)).click();
        } else {
            System.out.println(element +" element is not present");
        }
    }

    public void clickElmentByXpath(String element){
        if(waitForElement(element,5)){
            driver.findElementByXPath(element).click();
        } else {
            System.out.println(element +" element is not present");
        }

    }

    public void sendKeyById(String element,String value){
        if(waitForElement(element,2)){
            driver.findElementById(element).sendKeys(value);
        } else {
            System.out.println(element +" element is not present");
        }
    }

    public void switchWebView(AppiumDriver driver){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contextNames = driver.getContextHandles();
        for(String contextName : contextNames){
            if(contextName.contains("WEB")){
                System.out.println("开始切换混合应用视图");
                driver.context(contextName);
                System.out.println("切换WEBVIEW：" + driver.getContext());
                break;
            }
        }
    }



}
