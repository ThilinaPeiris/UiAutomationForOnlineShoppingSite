package com.bmsoft.tests;

import com.bmsoft.pages.*;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TOrderHistory extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TShippingAddress.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;
    private PLogin ploginObj;
    private POrderHistory pOrderHistoryObj;


    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            pOrderHistoryObj = new POrderHistory(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("Order History");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {

        driver.get(baseUrl);

        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(email);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();
    }

    //T12 - Verify order history records page is visible
    @Test
    public void viewOrderHistoryRecordsPage(){

        String pageTitle = ExcelUtil.getCellData(1, 1);

        pOrderHistoryObj.setTestResult(1, 2);

        phomeObj.clickMyAccountTab();
        pOrderHistoryObj.clickOrderHistorySideMenuBar();
        String title = pOrderHistoryObj.verifyTitle();
        Assert.assertEquals(title, pageTitle);
    }

    //T14 - Track the orders
    @Test
    public void trackOrder(){
        phomeObj.clickMyAccountTab();
        pOrderHistoryObj.clickOrderHistorySideMenuBar();
        pOrderHistoryObj.viewOrderRecord();
        pOrderHistoryObj.clickTrackLink();
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
