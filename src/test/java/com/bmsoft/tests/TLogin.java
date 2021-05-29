package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.PTerms;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
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

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PLogin pLogin;
    private PHome pHome;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);
            pLogin = new PLogin(driver, commonOpObj);
            pHome = new PHome(driver,commonOpObj);
            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("Login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
    }

    @Test
    public void tLoginWithValidCredentials() {

        String username = ExcelUtil.getCellData(1,1);
        String password = ExcelUtil.getCellData(1, 2);

        pHome.clickOnLoginLink();
        pLogin.enterUserNameAndPassword(username, password);
        pLogin.clickOnLoginBtn();

        //verification that user is successfully logged in to the system
        Assert.assertTrue(pHome.islogoutLinkIsDisplaying());
    }

    @Test
    public void tLoginWithValidUserNameAndInvalidPassword() {

        String username = ExcelUtil.getCellData(2,1);
        String password = ExcelUtil.getCellData(2, 2);

        pHome.clickOnLoginLink();
        pLogin.enterUserNameAndPassword(username, password);
        pLogin.clickOnLoginBtn();

        //verification that login error msg is showing..
        Assert.assertTrue(pLogin.isusernamePasswordValidationMsgSHowing());
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
            //commonOpObj.Sleep(2000);
        }

        driver.manage().deleteAllCookies();
        commonOpObj.Sleep(3000);

    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }


}
