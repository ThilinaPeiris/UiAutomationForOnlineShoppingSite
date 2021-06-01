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
    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

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

    //valid order id & valid email
    @Test
    public void tc_19(){

        pordertrackObj.setTestResult(1, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(1,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(1,2));
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        //verification
        String title =  phomeObj.navigateOrderTrackPage();
        Assert.assertEquals(title, ExcelUtil.getCellData(1,3));

    }

    //invalid order id
    @Test
    public void tc_20(){

        pordertrackObj.setTestResult(2, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(2,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(2,2));
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(2,3);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    //invalid email address
    @Test
    public void tc_21(){

        pordertrackObj.setTestResult(3, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(3,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(3,2));
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(3,3);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    //both invalid
    @Test
    public void tc_22(){

        pordertrackObj.setTestResult(4, 4);

        pordertrackObj.enterOrderId(ExcelUtil.getCellData(4,1));
        pordertrackObj.enterRegisteredEmail(ExcelUtil.getCellData(4,2));
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect= ExcelUtil.getCellData(4,3);
        // Verify error message
        Assert.assertEquals(actual_msg, expect);

    }

    /*

    //valid order id & valid email
    @Test
    public void tc_19(){
        pordertrackObj.enterOrderId("8");
        pordertrackObj.enterRegisteredEmail("thihari@gmail.com");
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        //verification
        String title =  phomeObj.navigateOrderTrackPage();
        Assert.assertEquals(title,"Order History");
    }

    //invalid order id
    @Test
    public void tc_20(){
        pordertrackObj.enterOrderId("1");
        pordertrackObj.enterRegisteredEmail("thihari@gmail.com");
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect="Either order id or Registered email id is invalid";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //invalid email address
    @Test
    public void tc_21(){
        pordertrackObj.enterOrderId("8");
        pordertrackObj.enterRegisteredEmail("thihari1212@gmail.com");
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect="Either order id or Registered email id is invalid";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
    }

    //both invalid
    @Test
    public void tc_22(){
        pordertrackObj.enterOrderId("1");
        pordertrackObj.enterRegisteredEmail("thihari1212@gmail.com");
        pordertrackObj.clickTrackBtn();
        //Thread.sleep(3000);

        String actual_msg=driver.findElement(By.xpath("//td[contains(text(),'Registered email id is invalid')]")).getText();
        // Store message in variable
        String expect="Either order id or Registered email id is invalid";
        // Verify error message
        Assert.assertEquals(actual_msg, expect);
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
