package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.POrderTrack;
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

public class TOrderTrack extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TOrderTrack.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private POrderTrack pordertrackObj;
    private PHome phomeObj;
    private PLogin ploginObj;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);
            pordertrackObj = new POrderTrack(driver, commonOpObj);
            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("TrackOrder");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);

        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress("thihari@gmail.com");
        ploginObj.enterPassword("abcde");
        phomeObj.clickTrackOrderBtn();
    }

    @Test(description = "LR_TC_19 - valid order id & valid email")
    public void orderTrackWithValidOidValidEmail() {
        pordertrackObj.setTestResult(1, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(1,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(1,2));
        pordertrackObj.clickTrackBtn();

        String title =  phomeObj.navigateOrderTrackPage();
        Assert.assertEquals(title, ExcelUtil.getCellData(1,3));
    }

    @Test(description = "LR_TC_20 - invalid order id")
    public void orderTrackWithInvalidOid() {
        pordertrackObj.setTestResult(2, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(2,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(2,2));
        pordertrackObj.clickTrackBtn();

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        String expect= ExcelUtil.getCellData(2,3);
        Assert.assertEquals(actual_msg, expect);
    }

    @Test(description = "LR_TC_21 - invalid email address")
    public void orderTrackWithInvalidEmailAddress() {
        pordertrackObj.setTestResult(3, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(3,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(3,2));
        pordertrackObj.clickTrackBtn();

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        String expect= ExcelUtil.getCellData(3,3);
        Assert.assertEquals(actual_msg, expect);
    }

    @Test(description = "LR_TC_21 - invalid oid & invalid email address")
    public void orderTrackWithInvalidOidInvalidEmailAddress() {
        pordertrackObj.setTestResult(4, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(4,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(4,2));
        pordertrackObj.clickTrackBtn();

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        String expect= ExcelUtil.getCellData(4,3);
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
