package com.bmsoft.tests;

import com.bmsoft.pages.PHome;
import com.bmsoft.testbase.BaseTest;
import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.SetupDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class THome extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(THome.class.getName());

    private WebDriver driver;
    private CommonOp commonOpObj;
    private PHome phomeObj;

    @BeforeClass
    public void setUpClass() {
        try {

            driver = SetupDriver.getDriver(driver, browser, baseUrl);

            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
            commonOpObj = new CommonOp(driver);

            phomeObj = new PHome(driver, commonOpObj);

            driver.manage().window().maximize();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
    }


    //CheckBox---------------------------------------------------
    @Test
    public void tclickOnBmwCheckbox() {
        LOGGER.debug("Add your logging entries here...");
        Assert.fail();
    }

    @Test
    @Ignore
    public void tclickOnBenzCheckbox() {
        Assert.assertTrue(true);
    }

    @Test
    @Ignore
    public void tclickOnHondaCheckbox() {
        Assert.assertTrue(true);
    }

    //RadioBtn----------------------------------------------------
    @Test
    @Ignore
    public void tCLickOnBenzadioBtn() {
        phomeObj.clickOnBenzradiobtn();
    }

    //SelectClass----------------------------------------------------
    @Test
    @Ignore
    public void tSelectSingleVal() {
        phomeObj.setSelectCarselectHondaVal();
    }

    @Test
    @Ignore
    public void tSelectMultipleVals() {
        phomeObj.setSelectmultiVals();
    }

    //OpenNewWindow-------------------------------------------------------------
    @Parameters({"childWindowURL"})
    @Test
    @Ignore
    public void tOpenNewWindow(String paramURL) {
        String parentWindowHandle = driver.getWindowHandle();
        //System.out.println(parentWindowHandle + "\n");
        phomeObj.clickOnOpenWindowBtn();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
            }
            //System.out.println(handle + "\n");
        }
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, paramURL);
        driver.switchTo().window(parentWindowHandle);
    }

    //OpenNewTab-------------------------------------------------------------
    @Test
    @Ignore
    public void tOpenNewTab() {
        String parentWindowHandle = driver.getWindowHandle();
        //System.out.println(parentWindowHandle + "\n");
        phomeObj.clickOnNewTabBtn();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
            }
            //System.out.println(handle + "\n");
        }
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://letskodeit.teachable.com/courses");
        driver.switchTo().window(parentWindowHandle);
    }

    //Alert-------------------------------------------------------------
    @Test
    @Ignore
    public void tHandleAlerts() {

        phomeObj.setNameInputField("Bhagya");
        phomeObj.clickOnAlertBtn();
        Assert.assertEquals(driver.switchTo().alert().getText(),
                "Hello Bhagya, share this practice page and share your knowledge");
        driver.switchTo().alert().accept();

        phomeObj.setNameInputField("Thilina");
        phomeObj.clickOnCOnfirmBtn();
        Assert.assertEquals(driver.switchTo().alert().getText(),
                "Hello Thilina, Are you sure you want to confirm?");
        driver.switchTo().alert().accept();

    }

    //ActionClass-------------------------------------------------------
    @Test
    @Ignore
    public void tActionCLass() {
        Actions builder = new Actions(driver);
        builder.moveToElement(phomeObj.getMouseHoverOverEle())
                .moveToElement(phomeObj.getMouseHoverOverReloadEle())
                .click()
                .build()
                .perform();

    }

    //------------------------------------------------------------------

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
