package com.bmsoft.tests;

import com.bmsoft.pages.PBooks;
import com.bmsoft.pages.PFurniture;
import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
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

public class TFurniture extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PFurniture pfurnitureObj;
    private PLogin ploginObj;
    private PHome pHomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            pfurnitureObj = new PFurniture(driver, commonOpObj);
            ploginObj = new PLogin(driver, commonOpObj);
            pHomeObj = new PHome(driver, commonOpObj);
            driver.manage().window().maximize();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
        //click login link
        pHomeObj.clickLoginbtn();
        ploginObj.enterEmailAddress(email);
        ploginObj.enterPassword(password);
        ploginObj.clickLogin();

    }
    //add to cart from furniture category - T10
    @Test(priority = 1,description = "test case T10")
    public void taddtoCart() {

        pfurnitureObj.selectFurniture();
        //addtocart
        pfurnitureObj.addtocartBtn();
        String addToCartAlert =  driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Product has been added to the cart",addToCartAlert);

    }
    //add to wishlist from furniture category -T11
    @Test(priority = 1,description = "test case T11")
    public void taddtoWishlist() {

        pfurnitureObj.selectFurniture();
        //add to wishlist
        pfurnitureObj.addtoWishListBtn();
        String title = pfurnitureObj.validateProductTitle();
        Assert.assertEquals(title,"My Wishlist");

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
