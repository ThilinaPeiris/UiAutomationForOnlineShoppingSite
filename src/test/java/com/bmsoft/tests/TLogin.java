package com.bmsoft.tests;

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

public class TLogin extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TLogin.class.getName());

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

            ExcelUtil.setExcelFileSheet("LoginData");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
        phomeObj.clickLoginbtn();
    }

    @Test(description = "LR_TC_09 - valid email address & valid password")
    public void loginWithValidEmailValidPassword() {
        ploginObj.setTestResult(1, 4);

        ploginObj.enterEmailAddress(ExcelUtil.getCellData(1,1));
        ploginObj.enterPassword(ExcelUtil.getCellData(1,2));
        ploginObj.clickLogin();

        String actual_msg=driver.findElement(By.xpath("//a[contains(text(),'Welcome -')]")).getText();
        String expect= ExcelUtil.getCellData(1,3);
        Assert.assertEquals(actual_msg,expect);
    }

    @Test(description = "LR_TC_10 - valid email address & invalid password")
    public void loginWithValidEmailInvalidPassword() {
        ploginObj.setTestResult(2, 4);

        ploginObj.enterEmailAddress(ExcelUtil.getCellData(2,1));
        ploginObj.enterPassword(ExcelUtil.getCellData(2,2));
        ploginObj.clickLogin();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Password')]")).getText();
        String expect= ExcelUtil.getCellData(2,3);
        Assert.assertEquals(actual_msg, expect);
    }

    @Test(description = "LR_TC_11 - invalid email address & valid password")
    public void loginWithInvalidEmailValidPassword() {
        ploginObj.setTestResult(3, 4);

        ploginObj.enterEmailAddress(ExcelUtil.getCellData(3,1));
        ploginObj.enterPassword(ExcelUtil.getCellData(3,2));
        ploginObj.clickLogin();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Password')]")).getText();
        String expect=ExcelUtil.getCellData(3,3);
        Assert.assertEquals(actual_msg, expect);
    }

    @Test(description = "LR_TC_12 - invalid email address & invalid password")
    public void LoginWithInvalidEmailInvalidPassword() {
        ploginObj.setTestResult(4, 4);

        ploginObj.enterEmailAddress(ExcelUtil.getCellData(4,1));
        ploginObj.enterPassword(ExcelUtil.getCellData(4,2));
        ploginObj.clickLogin();

        String actual_msg=driver.findElement(By.xpath("//span[contains(text(),'Invalid email id or Password')]")).getText();
        String expect=ExcelUtil.getCellData(4,3);
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
            commonOpObj.Sleep(2000);
        }
        driver.manage().deleteAllCookies();
        commonOpObj.Sleep(3000);
    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }
}
