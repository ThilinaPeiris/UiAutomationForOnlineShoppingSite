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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class THome extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;
    private PLogin ploginObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);
            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("HomeData");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
    }


    //successfully logout function verification
    @Test
    public void tc_01()throws InterruptedException {

        phomeObj.setTestResult(1, 4);

        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(ExcelUtil.getCellData(1,1));
        ploginObj.enterPassword(ExcelUtil.getCellData(1,2));
        ploginObj.clickLogin();
        Thread.sleep(3000);
        phomeObj.clickLogOutbtn();
        phomeObj.clickLoginbtn();

        //verification
        String actual = driver.findElement(By.xpath("//span[contains(text(),'You have successfully logout')]")).getText();
        String expected = ExcelUtil.getCellData(1,3);
        Assert.assertEquals(actual,expected);

    }
/*
    //successfully logout function verification
    @Test
    public void tc_01()throws InterruptedException {
        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress("thihari@gmail.com");
        ploginObj.enterPassword("12345");
        ploginObj.clickLogin();
        Thread.sleep(3000);
        phomeObj.clickLogOutbtn();
        phomeObj.clickLoginbtn();

        //verification
        String actual = driver.findElement(By.xpath("//span[contains(text(),'You have successfully logout')]")).getText();
        String expected = "You have successfully logout";
        Assert.assertEquals(actual,expected);
    }

*/
    @Test
    public void tselectHome() {
        phomeObj.selectHome();
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
