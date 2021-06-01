package com.bmsoft.tests;

import com.bmsoft.pages.PForgotPW;
import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class TForgotPW extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PForgotPW pforgotpwObj;
    private PLogin ploginObj;
    private PHome phomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);

            commonOpObj = new CommonOp(driver);
            pforgotpwObj = new PForgotPW(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            phomeObj = new PHome(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("ForgotPW");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
        phomeObj.clickLoginbtn();
    }

    //all valid fields
    @Test
    public void TC_13(){

        pforgotpwObj.setTestResult(1, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(1,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(1,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(1,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(1,4));
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Password Changed Successfully')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(1,5);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    //invalid email address
    @Test
    public void TC_14(){

        pforgotpwObj.setTestResult(2, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(2,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(2,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(2,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(2,4));
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(2,5);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    //invalid contact number
    @Test
    public void TC_15(){

        pforgotpwObj.setTestResult(3, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(3,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(3,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(3,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(3,4));
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(3,5);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    //empty email address
    @Test
    public void TC_16() {

        pforgotpwObj.setTestResult(4, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(4,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(4,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(4,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(4,4));
        pforgotpwObj.clickChangeBtn();

        //verification
        Assert.assertEquals(pforgotpwObj.emailPleaseFillMsgVerification(), ExcelUtil.getCellData(4,5));

    }

    //empty contact number
    @Test
    public void TC_17() {

        pforgotpwObj.setTestResult(5, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(5,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(5,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(5,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(5,4));
        pforgotpwObj.clickChangeBtn();

        //verification
        Assert.assertEquals(pforgotpwObj.contactNoPleaseFillMsgVerification(), ExcelUtil.getCellData(4,5));

    }

/*
    //all valid fields
    @Test(priority = 1)
    public void TC_13(){
        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress("thihari@gmail.com");
        pforgotpwObj.enterContactNo("0716693440");
        pforgotpwObj.enterPassword("12345");
        pforgotpwObj.enterConfirmPassword("12345");
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Password Changed Successfully')]")).getText();
        // Store message in variable
        String expect="Password Changed Successfully";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //invalid email address
    @Test
    public void TC_14(){
        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress("t@gmail.com");
        pforgotpwObj.enterContactNo("0716693440");
        pforgotpwObj.enterPassword("12345");
        pforgotpwObj.enterConfirmPassword("12345");
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        // Store message in variable
        String expect="Invalid email id or Contact no";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //invalid contact number
    @Test
    public void TC_15(){
        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress("thihari@gmail.com");
        pforgotpwObj.enterContactNo("0711212121");
        pforgotpwObj.enterPassword("12345");
        pforgotpwObj.enterConfirmPassword("12345");
        pforgotpwObj.clickChangeBtn();

        //verification
        // This will capture error message
        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        // Store message in variable
        String expect="Invalid email id or Contact no";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //empty email address
    @Test
    public void TC_16() {
        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress("");
        pforgotpwObj.enterContactNo("0716693440");
        pforgotpwObj.enterPassword("12345");
        pforgotpwObj.enterConfirmPassword("12345");
        pforgotpwObj.clickChangeBtn();

        //verification
        pforgotpwObj.emailPleaseFillMsgVerification();

    }

    //empty contact number
    @Test
    public void TC_17() {
        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress("thihari@gmail.com");
        pforgotpwObj.enterContactNo("");
        pforgotpwObj.enterPassword("12345");
        pforgotpwObj.enterConfirmPassword("12345");
        pforgotpwObj.clickChangeBtn();

        //verification
        pforgotpwObj.contactNoPleaseFillMsgVerification();

    }
*/

    @AfterMethod
    public void captureScreen(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target=new File(System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png");

            FileUtils.copyFile(source,target);
            commonOpObj.Sleep(2000);
        }

    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }
}
