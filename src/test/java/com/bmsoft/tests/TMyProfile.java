package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.PMyProfile;
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

public class TMyProfile extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TMyProfile.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;
    private PLogin ploginObj;
    private PMyProfile pmyProfileObj;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            pmyProfileObj = new PMyProfile(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("My Profile");

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

    @Test(description = "PM_TC_02 - Verify my account page is visible")
    public void viewMyAccountPage(){
        String pageTitle = ExcelUtil.getCellData(1,3);

        pmyProfileObj.setTestResult(1, 6);

        phomeObj.clickMyAccountTab();
        String title = pmyProfileObj.verifyTitle();
        Assert.assertEquals(title, pageTitle);
    }

    @Test(description = "PM_TC_03 - Update my profile with valid values")
    public void updateMyProfileDetails(){
        String nameValue = ExcelUtil.getCellData(2,1);
        String contactNoValue = ExcelUtil.getCellData(2, 2);
        String alertMessage = ExcelUtil.getCellData(2, 4);

        pmyProfileObj.setTestResult(2, 6);

        phomeObj.clickMyAccountTab();
        pmyProfileObj.clearValuesInNameTextbox();
        pmyProfileObj.enterName(nameValue);
        pmyProfileObj.clearValuesInContactNoTextbox();
        pmyProfileObj.enterContactNo(contactNoValue);
        pmyProfileObj.clickUpdateButton();

        String alertText = pmyProfileObj.getAlertText();
        pmyProfileObj.clickOkAlert();
        Assert.assertEquals(alertMessage,alertText);
    }

    @Test(description = "PM_TC_04 - Update my profile with empty values")
    public void updateMyProfileWithoutDetails(){
        String nameValue = ExcelUtil.getCellData(3,1);
        String contactNoValue = ExcelUtil.getCellData(3, 2);
        String formErrorMessage = ExcelUtil.getCellData(3, 5);

        pmyProfileObj.setTestResult(3, 6);

        phomeObj.clickMyAccountTab();
        pmyProfileObj.clearValuesInNameTextbox();
        pmyProfileObj.enterName(nameValue);
        pmyProfileObj.clearValuesInContactNoTextbox();
        pmyProfileObj.enterContactNo(contactNoValue);
        pmyProfileObj.clickUpdateButton();
        Assert.assertEquals(pmyProfileObj.invalidMsgValidation(),formErrorMessage);
    }

    @Test(description = "PM_TC_05 - Update my profile without name field")
    public void updateMyProfileWithoutName(){
        String nameValue = ExcelUtil.getCellData(4,1);
        String contactNoValue = ExcelUtil.getCellData(4, 2);
        String formErrorMessage = ExcelUtil.getCellData(4, 5);

        pmyProfileObj.setTestResult(4, 6);

        phomeObj.clickMyAccountTab();
        pmyProfileObj.clearValuesInNameTextbox();
        pmyProfileObj.enterName(nameValue);
        pmyProfileObj.clearValuesInContactNoTextbox();
        pmyProfileObj.enterContactNo(contactNoValue);
        pmyProfileObj.clickUpdateButton();
        Assert.assertEquals(pmyProfileObj.invalidMsgValidation(),formErrorMessage);
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
