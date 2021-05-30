package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.PSignUp;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
    @Test(priority =1)
    public void tc_02() {
        psignupObj.enterFullName("Max Smith Williams");
        psignupObj.enterEmail("maxsmith876@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();
        //driver.switchTo().alert().accept();
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

    }

    //empty email address
    @Test
    public void tc_04() {
        psignupObj.enterFullName("");
        psignupObj.enterEmail("max507@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("09876");
        psignupObj.clicksignup();

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

    }

    //different confirmation password
    @Test
    public void tc_08() {
        psignupObj.enterFullName("max123");
        psignupObj.enterEmail("max123@gmail.com");
        psignupObj.enterContactNo("0717788987");
        psignupObj.enterPword("09876");
        psignupObj.enterConfirmPword("abcde");
        psignupObj.clicksignup();

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


