package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.pages.PMyCart;
import com.bmsoft.pages.PPaymentMethod;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TMyCart extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TMyCart.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PLogin ploginObj;
    private PHome phomeObj;
    private PMyCart pMyCartObj;
    private PPaymentMethod pPaymentMethodObj;

    private String username , password , billingAddress , billingState , billingCity , billingPinCode , shippingAddress ,
            shippingState , shippingCity , shippingPinCode;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);

            commonOpObj = new CommonOp(driver);
            ploginObj = new PLogin(driver, commonOpObj);
            phomeObj = new PHome(driver,commonOpObj);
            pPaymentMethodObj = new PPaymentMethod(driver,commonOpObj);
            pMyCartObj = new PMyCart(driver, commonOpObj);

            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("MyCart");

            username = ExcelUtil.getCellData(2,1);
            password = ExcelUtil.getCellData(2, 2);
            List<String> billingAddressValues = Arrays.asList(ExcelUtil.getCellData(2, 3).split(":"));
            List<String> shippingAddressValues = Arrays.asList(ExcelUtil.getCellData(2, 4).split(":"));

            billingAddress = billingAddressValues.get(0);
            billingState = billingAddressValues.get(1);
            billingCity = billingAddressValues.get(2);
            billingPinCode = billingAddressValues.get(3);

            shippingAddress = shippingAddressValues.get(0);
            shippingState = shippingAddressValues.get(1);
            shippingCity = shippingAddressValues.get(2);
            shippingPinCode = shippingAddressValues.get(3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setupMethod(){
        driver.get(baseUrl);
    }

    @Test(description = "SC_TC_001 - Add to Cart")
    public void tAddToMyCartAlert(){
        phomeObj.clickOnRandomProductAddtoCartLink();

        String addToCartAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Product has been added to the cart", addToCartAlertText);

        pMyCartObj.setTestResult(1,5);
    }

    @Test(description = "SC_TC_015 -  Choose Payment Methods")
    public void tPaymentMethods(){
        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(username);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();
        pMyCartObj.clickOnHomeLink();

        phomeObj.clickOnRandomProductAddtoCartLink();
        driver.switchTo().alert().accept();

        setBillingShippingAddress();

        pMyCartObj.clickOnordersubmit();

        Assert.assertTrue(pPaymentMethodObj.iscodInputRadioBtnDisplayed());
        Assert.assertTrue(pPaymentMethodObj.isintentBankingInputRadioBtnDisplayed());
        Assert.assertTrue(pPaymentMethodObj.iscardInputRadioBtnDisplayed());

        pMyCartObj.setTestResult(2,5);
    }

    public void setBillingShippingAddress(){
        pMyCartObj.setBillingAddress(billingAddress);
        pMyCartObj.setbilingstate(billingState);
        pMyCartObj.setbillingcity(billingCity);
        pMyCartObj.setbillingpincode(billingPinCode);
        pMyCartObj.setShippingAddress(shippingAddress);
        pMyCartObj.setShippingstate(shippingState);
        pMyCartObj.setShippingcity(shippingCity);
        pMyCartObj.setShippingpincode(shippingPinCode);
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
