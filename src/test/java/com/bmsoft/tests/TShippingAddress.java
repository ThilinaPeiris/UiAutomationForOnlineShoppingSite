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

public class TShippingAddress extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TShippingAddress.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;
    private PLogin ploginObj;
    private PShippingAddress pShippingAddressObj;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            pShippingAddressObj = new PShippingAddress(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("Shipping Address");

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

    @Test(description = "PM_TC_11 - Update shipping address with valid values")
    public void updateShippingAddress(){
        String shippingAddressValue = ExcelUtil.getCellData(1,1);
        String shippingStateValue = ExcelUtil.getCellData(1, 2);
        String shippingCityValue = ExcelUtil.getCellData(1, 3);
        String shippingPincodeValue = ExcelUtil.getCellData(1, 4);
        String alertMessage = ExcelUtil.getCellData(1, 5);

        pShippingAddressObj.setTestResult(1, 6);

        phomeObj.clickMyAccountTab();
        pShippingAddressObj.clickShippingBillingAddressSideMenuBar();
        pShippingAddressObj.clickShippingAddressTab();
        pShippingAddressObj.enterShippingAddress(shippingAddressValue);
        pShippingAddressObj.enterShippingState(shippingStateValue);
        pShippingAddressObj.enterShippingCity(shippingCityValue);
        pShippingAddressObj.enterShippingPincode(shippingPincodeValue);
        pShippingAddressObj.clickUpdateButton();

        String alertText = pShippingAddressObj.getAlertText();
        pShippingAddressObj.clickOkAlert();
        Assert.assertEquals(alertMessage,alertText);
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
