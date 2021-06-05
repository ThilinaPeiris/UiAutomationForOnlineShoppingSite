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
    private static final Logger LOGGER = LogManager.getLogger(TForgotPW.class.getName());

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


    @Test(description = "TC_13 - all valid inputs")
    public void validInputs(){

        pforgotpwObj.setTestResult(1, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(1,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(1,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(1,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(1,4));
        pforgotpwObj.clickChangeBtn();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Password Changed Successfully')]")).getText();
        String expect= ExcelUtil.getCellData(1,5);
        Assert.assertEquals(actual_msg, expect);

    }


    @Test(description = "TC_14 - invalid email address")
    public void invalidEmailAddress(){

        pforgotpwObj.setTestResult(2, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(2,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(2,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(2,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(2,4));
        pforgotpwObj.clickChangeBtn();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        String expect= ExcelUtil.getCellData(2,5);
        Assert.assertEquals(actual_msg, expect);

    }


    @Test(description = "TC_15 - invalid contact number")
    public void invalidContactNumber(){

        pforgotpwObj.setTestResult(3, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(3,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(3,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(3,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(3,4));
        pforgotpwObj.clickChangeBtn();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Contact no')]")).getText();
        String expect= ExcelUtil.getCellData(3,5);
        Assert.assertEquals(actual_msg, expect);

    }


    @Test(description = "TC_16 - empty email address")
    public void emptyEmailAddress() {

        pforgotpwObj.setTestResult(4, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(4,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(4,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(4,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(4,4));
        pforgotpwObj.clickChangeBtn();

        Assert.assertEquals(pforgotpwObj.emailPleaseFillMsgVerification(), ExcelUtil.getCellData(4,5));

    }


    @Test(description = "TC_17 - empty contact number")
    public void emptyContactNumber() {

        pforgotpwObj.setTestResult(5, 6);

        ploginObj.clickForgotPassword();
        pforgotpwObj.enterEmailAddress(ExcelUtil.getCellData(5,1));
        pforgotpwObj.enterContactNo(ExcelUtil.getCellData(5,2));
        pforgotpwObj.enterPassword(ExcelUtil.getCellData(5,3));
        pforgotpwObj.enterConfirmPassword(ExcelUtil.getCellData(5,4));
        pforgotpwObj.clickChangeBtn();

        Assert.assertEquals(pforgotpwObj.contactNoPleaseFillMsgVerification(), ExcelUtil.getCellData(4,5));

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
            commonOpObj.Sleep(2000);
        }

    }


    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }
}
