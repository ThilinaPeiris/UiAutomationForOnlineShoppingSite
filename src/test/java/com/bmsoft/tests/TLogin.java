package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TLogin extends BaseTest {

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PLogin ploginObj;
    private PHome phomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);
            ploginObj = new PLogin(driver, commonOpObj);
            phomeObj = new PHome(driver,commonOpObj);
            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("Login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
    }

//    @Test
//    public void tLoginWithValidCredentials() {
//
//        String username = ExcelUtil.getCellData(1,1);
//        String password = ExcelUtil.getCellData(1, 2);
//
//        pHome.clickOnLoginLink();
//        pLogin.enterUserNameAndPassword(username, password);
//        pLogin.clickOnLoginBtn();
//
//        //verification that user is successfully logged in to the system
//        Assert.assertTrue(pHome.islogoutLinkIsDisplaying());
//        pLogin.setTestResult(1, 4);
//    }
//
//    @Test
//    public void tLoginWithValidUserNameAndInvalidPassword() {
//
//        String username = ExcelUtil.getCellData(2,1);
//        String password = ExcelUtil.getCellData(2, 2);
//
//        pHome.clickOnLoginLink();
//        pLogin.enterUserNameAndPassword(username, password);
//        pLogin.clickOnLoginBtn();
//
//        //verification that login error msg is showing..
//        Assert.assertTrue(pLogin.isusernamePasswordValidationMsgSHowing());
//        pLogin.setTestResult(2, 4);
//    }

    //valid email address & password
    @Test(priority = 1)
    public void TC_09() throws InterruptedException {
        //Enter username & password
        ploginObj.enterEmailAddress("abc@gmail.com");
        ploginObj.enterPassword("abcde");

        //Click on login button
        ploginObj.clickLogin();
        Thread.sleep(3000);

        //verification
        if (driver.getPageSource().contains("Welcome")){
            System.out.println(("Text is present"));
        }else{
            System.out.println("Text is absent");
        }

        phomeObj.clickLogOutbtn();

    }

    //valid email address & invalid password
    @Test
    public void TC_10() throws InterruptedException{
        ploginObj.enterEmailAddress("abc@gmail.com");
        ploginObj.enterPassword("12345");
        ploginObj.clickLogin();
        Thread.sleep(3000);

        //verification

        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[1]/form/span")).getText();
        // Store message in variable
        String expect="Invalid email id or Password";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //invalid email address & valid password
    @Test
    public void TC_11() throws InterruptedException{
        ploginObj.enterEmailAddress("abc123456@gmail.com");
        ploginObj.enterPassword("12345");
        ploginObj.clickLogin();
        Thread.sleep(3000);
        //verification

        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[1]/form/span")).getText();
        // Store message in variable
        String expect="Invalid email id or Password";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //invalid email address & invalid password
    @Test
    public void TC_12() throws InterruptedException{
        ploginObj.enterEmailAddress("123abc@gmail.com");
        ploginObj.enterPassword("89545");
        ploginObj.clickLogin();
        Thread.sleep(3000);
        //verification

        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[1]/form/span")).getText();
        // Store message in variable
        String expect="Invalid email id or Password";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }



    @AfterMethod
    public void captureScreen(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target=new File(System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png");

            FileUtils.copyFile(source,target);
            //commonOpObj.Sleep(2000);
        }

        driver.manage().deleteAllCookies();
        commonOpObj.Sleep(3000);

    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }


}
