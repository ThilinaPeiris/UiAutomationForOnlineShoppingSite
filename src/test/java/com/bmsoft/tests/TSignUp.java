package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.PSignUp;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TSignUp extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PSignUp psignupObj;
    private PHome phomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            psignupObj = new PSignUp(driver, commonOpObj);
            phomeObj = new PHome(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("SignUpData");

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
    public void tc_02() throws InterruptedException {

        psignupObj.setTestResult(1, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(1,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(1,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(1,3));
        psignupObj.enterPword(ExcelUtil.getCellData(1,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(1,5));
        psignupObj.clicksignup();
        Thread.sleep(3000);

        //verification
        String successfullyRegisterAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(ExcelUtil.getCellData(1,6),successfullyRegisterAlertText);

    }

    //empty full name
    @Test
    public void tc_03() {

        psignupObj.setTestResult(2, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(2,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(2,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(2,3));
        psignupObj.enterPword(ExcelUtil.getCellData(2,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(2,5));
        psignupObj.clicksignup();

        //verification
        Assert.assertEquals(psignupObj.fullNamePleaseFillMsgVerification(), ExcelUtil.getCellData(2,6));

    }

    //empty email address
    @Test
    public void tc_04() {

        psignupObj.setTestResult(3, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(3,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(3,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(3,3));
        psignupObj.enterPword(ExcelUtil.getCellData(3,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(3,5));
        psignupObj.clicksignup();

        //verification
        Assert.assertEquals(psignupObj.emailPleaseFillMsgVerification(), ExcelUtil.getCellData(3,6));

    }

    //empty contact no
    @Test
    public void tc_05() {

        psignupObj.setTestResult(4, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(4,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(4,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(4,3));
        psignupObj.enterPword(ExcelUtil.getCellData(4,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(4,5));
        psignupObj.clicksignup();

        //verification
        Assert.assertEquals(psignupObj.contactNoPleaseFillMsgVerification(), ExcelUtil.getCellData(4,6));

    }

    //empty password
    @Test
    public void tc_06() {

        psignupObj.setTestResult(5, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(5,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(5,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(5,3));
        psignupObj.enterPword(ExcelUtil.getCellData(5,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(5,5));
        psignupObj.clicksignup();

        //verification
        Assert.assertEquals(psignupObj.passwordPleaseFillMsgVerification(), ExcelUtil.getCellData(5,6));

    }

    //empty confirm password
    @Test
    public void tc_07() {

        psignupObj.setTestResult(6, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(6,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(6,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(6,3));
        psignupObj.enterPword(ExcelUtil.getCellData(6,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(6,5));
        psignupObj.clicksignup();

        //verification
        Assert.assertEquals(psignupObj.cPasswordPleaseFillMsgVerification(), ExcelUtil.getCellData(6,6));

    }

    //different confirmation password
    @Test
    public void tc_08() throws InterruptedException {

        psignupObj.setTestResult(7, 7);

        psignupObj.enterFullName(ExcelUtil.getCellData(7,1));
        psignupObj.enterEmail(ExcelUtil.getCellData(7,2));
        psignupObj.enterContactNo(ExcelUtil.getCellData(7,3));
        psignupObj.enterPword(ExcelUtil.getCellData(7,4));
        psignupObj.enterConfirmPword(ExcelUtil.getCellData(7,5));
        psignupObj.clicksignup();
        Thread.sleep(3000);

        //validation
        String passwordDifferentAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(ExcelUtil.getCellData(7,6),passwordDifferentAlertText);

    }
/*
    //all valid fields
    @Test
    public void tc_02() throws InterruptedException {
        psignupObj.enterFullName("Max Smith Williams");
        psignupObj.enterEmail("maxs123@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("12345");
        psignupObj.enterConfirmPword("12345");
        psignupObj.clicksignup();
        Thread.sleep(3000);

        //verification
        String successfullyRegisterAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("You are successfully register",successfullyRegisterAlertText);
    }

    //empty full name
    @Test
    public void tc_03() {
        psignupObj.enterFullName("");
        psignupObj.enterEmail("max841@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();

        //verification
        psignupObj.fullNamePleaseFillMsgVerification();
    }

    //empty email address
    @Test
    public void tc_04() {
        psignupObj.enterFullName("john max");
        psignupObj.enterEmail("");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();

        //verification
        psignupObj.emailPleaseFillMsgVerification();
    }

    //empty contact no
    @Test
    public void tc_05() {
        psignupObj.enterFullName("max567");
        psignupObj.enterEmail("max567@gmail.com");
        psignupObj.enterContactNo("");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();

        //verification
        psignupObj.contactNoPleaseFillMsgVerification();
    }

    //empty password
    @Test
    public void tc_06() {
        psignupObj.enterFullName("max876");
        psignupObj.enterEmail("max876@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();

        //verification
        psignupObj.passwordPleaseFillMsgVerification();

    }

    //empty confirm password
    @Test
    public void tc_07() {
        psignupObj.enterFullName("max234");
        psignupObj.enterEmail("max234@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("");
        psignupObj.clicksignup();

        //verification
        psignupObj.cPasswordPleaseFillMsgVerification();

    }

    //different confirmation password
    @Test
    public void tc_08() throws InterruptedException {
        psignupObj.enterFullName("max123");
        psignupObj.enterEmail("max184@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("abcde");
        psignupObj.clicksignup();
        Thread.sleep(3000);

        //validation
        String passwordDifferentAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Password and Confirm Password Field do not match  !!",passwordDifferentAlertText);
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


