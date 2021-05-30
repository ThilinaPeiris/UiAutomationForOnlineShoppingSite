package com.bmsoft.tests;

import com.bmsoft.pages.PForgotPW;
import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
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
        String actual_msg = driver.findElement(By.name("email")).getAttribute("Please fill out this field");
        String expect="Please fill out this field";//?
        Assert.assertEquals(actual_msg, expect);//?

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
