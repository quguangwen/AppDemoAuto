
import com.util.ReadProperty;
import com.util.TestData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;


public class TestDemo {

    DesiredCapabilities capabilities = null;
    AndroidDriver driver = null;
    TouchAction touchAction = null;
    WebDriver webDriver = null;


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
            WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean waitForWebElementXpath(String element, int timeOutSeconds){
        try{
            //this.webDriver = driver;
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
        if(waitForWebElementXpath(element,10)){
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

    public void switchWebView(AndroidDriver driver){

            Set <String> contextNames = driver.getContextHandles();
            for(String contextName : contextNames){
                if(contextName.contains("WEB")){
                    driver.context(contextName);
                    System.out.println("切换成功：" + driver.getContext());
                    this.webDriver = driver;
                }
            }
    }

    public void swipeScreenPortrat(){
        (new TouchAction(driver))
                .press(PointOption.point(676,1879))
                .moveTo(PointOption.point(707,578))
                .release()
                .perform();
    }

    public WebElement findByElementFlag(String textflag, String webElementFlag){
        WebElement webElement = null;
        try{
            List<WebElement> webElements = driver.findElementsByClassName(webElementFlag);
            WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
            for(int i=0; i<webElements.size(); i++){
                if(webElements.get(i).getText().equals(textflag)){
                    webElement = webElements.get(i);
                    //System.out.println(webElement.getText());
                    break;
                }
            }
            webElements = null;
            return webElement;
        }catch (Exception e){
            return null;
        }
    }

    @Test(dataProvider = "testData", dataProviderClass= TestData.class)
    public void testLogin() throws MalformedURLException, InterruptedException {

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ReadProperty.getStateCNValue("android.platformName"));
        capabilities.setCapability("automationName", ReadProperty.getStateCNValue("android.automationName"));
        capabilities.setCapability("platformVersion", ReadProperty.getStateCNValue("android.platformVersion"));
        capabilities.setCapability("deviceName", ReadProperty.getStateCNValue("android.deviceName"));
        capabilities.setCapability("app", ReadProperty.getStateCNValue("android.app"));
        capabilities.setCapability("appPackage", ReadProperty.getStateCNValue("android.appPackage"));
        capabilities.setCapability("appActivity", ReadProperty.getStateCNValue("android.appActivity"));
        capabilities.setCapability("unicodeKeyboard", ReadProperty.getStateCNValue("android.unicodeKeyboard"));
        capabilities.setCapability("recreateChromeDriverSessions",ReadProperty.getStateCNValue("android.recreateChromeDriverSessions"));
        Thread.sleep(2000);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

/*
 *************************** 注册新用户*************************************
 */

        clickElementByID("com.android.packageinstaller:id/permission_allow_button");
        clickElementByID("com.android.packageinstaller:id/permission_allow_button");
        clickElementByID("com.puhuicredit.app:id/loan_bt");
        clickElementByID("com.puhuicredit.app:id/regist_bt");
        sendKeyById("com.puhuicredit.app:id/mobile_et","18618430266"); //18618430266
        sendKeyById("com.puhuicredit.app:id/code_et","000000");
        //driver.hideKeyboard();
        clickElementByID("com.puhuicredit.app:id/company_cb");
        clickElementByID("com.puhuicredit.app:id/login_bt");
        Thread.sleep(5000);
        switchWebView(driver);
        clickWebElementXpath("//*[@id=\"app\"]/div/div[2]/div/button[2]"); ////*[@id="app"]/div/div[2]/div/button[1]
        Thread.sleep(1000);
        driver.context("NATIVE_APP");

        sendKeyById("com.puhuicredit.app:id/new_pw","a12345678");
        sendKeyById("com.puhuicredit.app:id/re_new_pw","a12345678");
        clickElementByID("com.puhuicredit.app:id/now_set_bt");

/*
 ***************************设置手势密码************************************
 */
        Thread.sleep(1000);
        touchAction = new TouchAction(driver);
        if(waitForElement("com.puhuicredit.app:id/top_hint",2)){
            String text = driver.findElementById("com.puhuicredit.app:id/top_hint").getText();
            System.out.println(text);
            if(text.equals("请设置手势密码")){
                touchAction.press(PointOption.point(299,919))
                        .moveTo(PointOption.point(294,1347))
                        .moveTo(PointOption.point(299,1760))
                        .moveTo(PointOption.point(723,1765))
                        .release()
                        .perform();
            }

        }

        if(waitForElement("com.puhuicredit.app:id/top_hint",2)){
            String text = driver.findElementById("com.puhuicredit.app:id/top_hint").getText();
            System.out.println(text);
            if(text.equals("请重复手势密码")){
                touchAction.press(PointOption.point(299,919))
                        .moveTo(PointOption.point(294,1347))
                        .moveTo(PointOption.point(299,1760))
                        .moveTo(PointOption.point(723,1765))
                        .release()
                        .perform();
            }
        }


/**************************** 实名认证***********************************/

        if(waitForElement("com.puhuicredit.app:id/arc_progress",10)){
            clickElementByID("com.puhuicredit.app:id/loan_bt");

            if(waitForElement("com.puhuicredit.app:id/upload_widget_font",4)){
                clickElementByID("com.puhuicredit.app:id/upload_widget_font");
                clickElementByID("com.puhuicredit.app:id/tv_take_pics");
                Thread.sleep(4000);
                touchAction.tap(PointOption.point(201,382)).perform();
            }

            if(waitForElement("com.puhuicredit.app:id/upload_widget_back",2)){
                clickElementByID("com.puhuicredit.app:id/upload_widget_back");
                clickElementByID("com.puhuicredit.app:id/tv_take_pics");
                Thread.sleep(4000);
                touchAction.tap(PointOption.point(552,366)).perform();
            }

            if(waitForElement("com.puhuicredit.app:id/name",2)){
                sendKeyById("com.puhuicredit.app:id/name","自测三");
                sendKeyById("com.puhuicredit.app:id/id_no","110101198709074757");
                Thread.sleep(1000);
                driver.hideKeyboard();
                sendKeyById("com.puhuicredit.app:id/manner_no_et","024720");
                clickElementByID("com.puhuicredit.app:id/tv_associate_sales_number");
                driver.hideKeyboard();
                swipeScreenPortrat();
                clickElementByID("com.puhuicredit.app:id/next_bt");
            }


        }



/*
*******************************测一测，选择产品，搜索产品**********************************
 */
        //touchAction.tap(PointOption.point(274,614)).perform();
        Thread.sleep(5000);
        if(waitForElementTobeClickable("com.puhuicredit.app:id/tv_test_right_now")){
            clickElementByID("com.puhuicredit.app:id/tv_test_right_now");
            Thread.sleep(2000);
            switchWebView(driver);
            clickWebElementXpath("//*[@id=\"app\"]/div/div[2]/button[1]");
            Thread.sleep(2000);
            clickWebElementXpath("//*[@id=\"app\"]/div/div[2]/button[2]");
            Thread.sleep(2000);
            driver.context("NATIVE_APP");

        }

        if(waitForElementTobeClickable("com.puhuicredit.app:id/right_text")){
            clickElementByID("com.puhuicredit.app:id/right_text");
            clickElementByID("com.puhuicredit.app:id/tv_test_right_now");
            Thread.sleep(2000);
            switchWebView(driver);
            System.out.println(webDriver.getPageSource());
            clickWebElementXpath("//*[@id=\"app\"]/div/div[2]/button[1]");
            Thread.sleep(2000);
            clickWebElementXpath("//*[@id=\"app\"]/div/div[2]/button[2]");
            Thread.sleep(2000);
            driver.context("NATIVE_APP");

            System.out.println("切换成NATIVE_APP");
        }
        if(waitForElementTobeClickable("com.puhuicredit.app:id/et_search")){
            clickElementByID("com.puhuicredit.app:id/et_search");
            sendKeyById("com.puhuicredit.app:id/et_search","才薪贷");
            clickElementByID("com.puhuicredit.app:id/tv_enter");
            driver.findElementByAndroidUIAutomator("text(\"立即借款\")").click();
            Thread.sleep(2000);
        }
        if(waitForElementTobeClickable("com.puhuicredit.app:id/et_loan_minimum")){
            clickElementByID("com.puhuicredit.app:id/et_loan_minimum");
            Thread.sleep(2000);
            sendKeyById("com.puhuicredit.app:id/et_loan_minimum","20000");
            Thread.sleep(2000);
            clickElementByID("com.puhuicredit.app:id/tv_select");
            Thread.sleep(2000);
            driver.findElementByAndroidUIAutomator("text(\"装修\")").click();
            Thread.sleep(2000);
            driver.findElementByAndroidUIAutomator("text(\"提交申请\")").click();
            Thread.sleep(2000);
            //Thread.sleep(2000);
            swipeScreenPortrat();

            switchWebView(driver);
            Thread.sleep(2000);
            clickWebElementXpath("//*[@id=\"app\"]/div/button");
            Thread.sleep(3000);
            driver.context("NATIVE_APP");
            Thread.sleep(2000);
            clickElementByID("com.puhuicredit.app:id/tv_ok");
        }
/************************基础信息填写*****************************/
        clickElementByID("com.puhuicredit.app:id/step_desc");
        Thread.sleep(3000);
        findByElementFlag("基本信息","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("学历","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("本科","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("婚姻状况","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("未婚","android.widget.TextView").click();
        sendKeyById("com.puhuicredit.app:id/et_personal_email","111222333@qq.com");
        Thread.sleep(2000);
        clickElementByID("com.puhuicredit.app:id/btn_commit_one");


/**********************居住信息填写*********/

        findByElementFlag("居住情况","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("自有房产","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("现居住地","android.widget.TextView").click();
        Thread.sleep(4000);
        switchWebView(driver);
        Thread.sleep(3000);
        //System.out.println(webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div[1]/ul/li[1]")).getText());
        clickWebElementXpath("//*[@id=\"app\"]/div[2]/div[1]/ul/li[1]");
        Thread.sleep(3000);
        clickWebElementXpath("//*[@id=\"app\"]/div[2]/div[1]/ul/li[1]");
        Thread.sleep(3000);
        clickWebElementXpath("//*[@id=\"app\"]/div[2]/div[1]/ul/li[1]");
        Thread.sleep(2000);
        driver.context("NATIVE_APP");
        Thread.sleep(1000);
        sendKeyById("com.puhuicredit.app:id/et_personal_detail_address","朝阳区街道办");
        sendKeyById("com.puhuicredit.app:id/et_personal_live_age", "30");
        clickElementByID("com.puhuicredit.app:id/btn_commit_two");

/*******************资产信息填写****************************/
        Thread.sleep(3000);
        findByElementFlag("本市房产","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("有房无贷","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("车产","android.widget.TextView").click();
        Thread.sleep(2000);
        findByElementFlag("无车","android.widget.TextView").click();
        sendKeyById("com.puhuicredit.app:id/et_personal_incomeByAge","70000");
        sendKeyById("com.puhuicredit.app:id/et_personal_bankcardNo", "6226220130594426");
        Thread.sleep(3000);
        findByElementFlag("还款来源","android.widget.TextView").click();
        Thread.sleep(3000);
        findByElementFlag("工资收入","android.widget.TextView").click();
        clickElementByID("com.puhuicredit.app:id/btn_commit_three");
        Thread.sleep(8000);
        driver.quit();

    }








}
