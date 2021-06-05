package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.pages.PLogin;
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

public class TSearch extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(TSearch.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;
    private PLogin ploginObj;

    @BeforeClass
    public void setUpClass() {
        try {
            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            phomeObj = new PHome(driver, commonOpObj);
            ploginObj = new PLogin(driver,commonOpObj);

            driver.manage().window().maximize();
            ExcelUtil.setExcelFileSheet("Search");

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

    @Test(description = "C_TC_13 - Search  product")
    public void tSearchProduct() {
        phomeObj.selectHome();

        String search = ExcelUtil.getCellData(1,1);
        phomeObj.searchField(search);

        String title = phomeObj.validateProductTitle();
        Assert.assertEquals(title,"Product Category");
        phomeObj.setTestResult(1, 2);
    }

    @Test(description = "C_TC_14 - Invalid search  product")
    public void tInvalidSearchProduct() {
        phomeObj.selectHome();

        String title = phomeObj.validateProductTitle();
        Assert.assertEquals(title,"Shopping Portal Home Page");

        String invalidData = ExcelUtil.getCellData(2,1);
        phomeObj.searchField(invalidData);
        if (driver.getPageSource().contains("No Product Found")) {
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }
        phomeObj.setTestResult(2, 2);
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
