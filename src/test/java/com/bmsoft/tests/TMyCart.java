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
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TMyCart extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TMyCart.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PLogin ploginObj;
    private PHome phomeObj;
    private PMyCart pMyCartObj;
    private PPaymentMethod pPaymentMethodObj;

    private String username, password, billingAddress, billingState, billingCity, billingPinCode, shippingAddress,
            shippingState, shippingCity, shippingPinCode;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);
            ploginObj = new PLogin(driver, commonOpObj);
            phomeObj = new PHome(driver, commonOpObj);
            pPaymentMethodObj = new PPaymentMethod(driver, commonOpObj);
            pMyCartObj = new PMyCart(driver, commonOpObj);
            driver.manage().window().maximize();

            ExcelUtil.setExcelFileSheet("MyCart");

            username = ExcelUtil.getCellData(1, 1);
            password = ExcelUtil.getCellData(1, 2);
            List<String> billingAddressValues = Arrays.asList(ExcelUtil.getCellData(1, 3).split(":"));
            List<String> shippingAddressValues = Arrays.asList(ExcelUtil.getCellData(1, 4).split(":"));

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
    public void setupMethod() {
        driver.get(baseUrl);
    }

    @Test(description = "SC_TC_001 - Add to Cart")
    public void tAddToMyCartAlert() {
        phomeObj.clickOnRandomProductAddtoCartLink();
        commonOpObj.waitUntilAlertIsPresent(2);

        String addToCartAlertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Product has been added to the cart", addToCartAlertText);

        pMyCartObj.setTestResult(1, 5);
    }

    @Test(description = "SC_TC_015 -  Choose Payment Methods")
    public void tPaymentMethods() {
        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(username);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();
        pMyCartObj.clickOnHomeLink();

        phomeObj.clickOnRandomProductAddtoCartLink();
        commonOpObj.waitUntilAlertIsPresent(2);
        driver.switchTo().alert().accept();

        setBillingShippingAddress();
        pMyCartObj.clickOnordersubmit();

        Assert.assertTrue(pPaymentMethodObj.iscodInputRadioBtnDisplayed());
        Assert.assertTrue(pPaymentMethodObj.isintentBankingInputRadioBtnDisplayed());
        Assert.assertTrue(pPaymentMethodObj.iscardInputRadioBtnDisplayed());

        pMyCartObj.setTestResult(2, 5);
    }

    @Test(description = "SC_TC_002 - Add single item to the shopping cart")
    public void tAddSingleItemInToTheShoppingCart() {
        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(username);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();
        pMyCartObj.clickOnHomeLink();
        phomeObj.scrollIntoProduct();

        String randomProductName = phomeObj.getRandomProductName();
        String randomProductPrice = phomeObj.getRandomProductPrice().replaceAll("[^0-9]", "");

        phomeObj.clickOnRandomProductAddtoCartLink();
        commonOpObj.waitUntilAlertIsPresent(2);
        driver.switchTo().alert().accept();

        boolean productFound = false;

        Map<String, List<String>> addedProducts = pMyCartObj.getAllTheSHoppingCartDetails();

        for (int i = 0; i < addedProducts.size(); i++) {
            List<String> productDetails = addedProducts.get(Integer.toString(i));
            String pName = productDetails.get(0);
            String pQuantity = productDetails.get(1);
            String pPrice = productDetails.get(2);
            String pShippingPrice = productDetails.get(3);
            String pGrandTotal = productDetails.get(4);

            String priceWithoutDecimal = pPrice.substring(0, pPrice.length() - 3).replaceAll("[^0-9]", "");

            if (pName.equalsIgnoreCase(randomProductName)) {
                Assert.assertEquals(randomProductPrice, priceWithoutDecimal);
                Assert.assertEquals("1", pQuantity);
                productFound = true;
            }
        }
        Assert.assertTrue(productFound);
        pMyCartObj.setTestResult(3, 5);
    }

    @Test(description = "SC_TC_004- Add single item, multiple times to the shopping cart", enabled = false)
    public void tAddSingleItemMultipleTimesInToTheShoppingCart() {
        String randomProductName = null;
        String randomProductPrice = null;

        phomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(username);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();
        pMyCartObj.clickOnHomeLink();

        phomeObj.scrollIntoProduct();
        randomProductName = phomeObj.getRandomProductName();
        randomProductPrice = phomeObj.getRandomProductPrice().replaceAll("[^0-9]", "");

        for (int i = 0; i < 3; i++) {
            pMyCartObj.clickOnHomeLink();
            phomeObj.scrollIntoProduct();

            phomeObj.clickOnRandomProductAddtoCartLink();
            commonOpObj.waitUntilAlertIsPresent(5);
            driver.switchTo().alert().accept();
        }

        boolean productFound = false;
        Map<String, List<String>> addedProducts = pMyCartObj.getAllTheSHoppingCartDetails();

        for (int i = 0; i < addedProducts.size(); i++) {
            List<String> productDetails = addedProducts.get(Integer.toString(i));
            String pName = productDetails.get(0);
            String pQuantity = productDetails.get(1);
            String pPrice = productDetails.get(2);
            String pShippingPrice = productDetails.get(3);
            String pGrandTotal = productDetails.get(4);

            String priceWithoutDecimal = pPrice.substring(0, pPrice.length() - 3).replaceAll("[^0-9]", "");
            String pGrandTotalWithoutDecimal = pGrandTotal.substring(0, pGrandTotal.length() - 3).replaceAll("[^0-9]", "");
            String pSHippingWithoutDecimal = pShippingPrice.substring(0, pShippingPrice.length() - 3).replaceAll("[^0-9]", "");

            if (pName.equalsIgnoreCase(randomProductName)) {
                Assert.assertEquals(randomProductPrice, priceWithoutDecimal);

                int pTotal = Integer.parseInt(randomProductPrice) * 3 + Integer.parseInt(pSHippingWithoutDecimal);
                Assert.assertEquals(Integer.toString(pTotal), pGrandTotalWithoutDecimal);

                Assert.assertEquals("3", pQuantity);
                productFound = true;
            }
        }
        Assert.assertTrue(productFound);
        pMyCartObj.setTestResult(4, 5);
    }

    public void setBillingShippingAddress() {
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
    public void captureScreen(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target = new File(System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png");

            FileUtils.copyFile(source, target);
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
