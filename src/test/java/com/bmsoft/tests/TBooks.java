package com.bmsoft.tests;

import com.bmsoft.pages.PBooks;
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

public class TBooks extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PBooks pBooksObj;
    private PLogin ploginObj;
    private PHome pHomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            pBooksObj = new PBooks(driver, commonOpObj);
            ploginObj = new PLogin(driver,commonOpObj);
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
    //T06
    @Test(priority = 1,description = "test case T06")
    public void tselectProduct() {
        //select book category - T06
        pBooksObj.selectBooks();
        //select wimpy kid book
        pBooksObj.selectProduct();
        String title = pBooksObj.validateProductTitle();
        Assert.assertEquals(title,"Product Details");
        //verify the name of the book
        if (driver.getPageSource().contains("The Wimpy Kid Do -It- Yourself Book")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }
    }

    //T07
    @Test(priority = 1,description = "test case T07")
    public void tselectImages() {
        //select book category
        pBooksObj.selectBooks();
        //select wimpy kid book
        pBooksObj.selectProduct();
        String title = pBooksObj.validateProductTitle();
        Assert.assertEquals(title,"Product Details");
        //verify the name of the book
        if (driver.getPageSource().contains("The Wimpy Kid Do -It- Yourself Book")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }
        //select the image of the book - T07
        pBooksObj.selectImg();
        //click next of the image
        pBooksObj.clickImgNext();
    }


    //test case T08
    @Test(priority = 1,description = "test case T08")
    public void taddtoCart() {
        pBooksObj.selectBooks();
        pBooksObj.selectProduct();
        if (driver.getPageSource().contains("The Wimpy Kid Do -It- Yourself Book")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }
        pBooksObj.addToCart();
        String addToCartAlert =  driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Product has been added to the cart",addToCartAlert);

        //pBooksObj.setTestResult(1,3);
    }

    //test case T09
    @Test(priority = 1,description = "test case T09")
    public void taddtoWishList() {
        pBooksObj.selectBooks();
        pBooksObj.selectProduct();
        //verify the text is present
        if (driver.getPageSource().contains("The Wimpy Kid Do -It- Yourself Book")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }
        pBooksObj.addToWishlist();
        //assert
        String title = pBooksObj.validateProductTitle();
        Assert.assertEquals(title,"My Wishlist");
    }
    //T12
    @Test(priority = 1,description = "test case T12")
    public void tcheckAvailability() {
        //select book category - T06
        pBooksObj.selectBooks();
        //select wimpy kid book
        pBooksObj.selectProduct();
        String title = pBooksObj.validateProductTitle();
        Assert.assertEquals(title,"Product Details");
        //verify the name of the book
        if (driver.getPageSource().contains("In Stock")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Not available");
        }
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

