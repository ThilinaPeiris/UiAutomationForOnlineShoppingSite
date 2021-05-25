package com.bmsoft.utilities;

import java.io.IOException;
import java.util.Arrays;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.base.Throwables;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends TestListenerAdapter
{

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;


    public void onStart(ITestContext testContext)
    {
        extentSparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/TestReport.html");//specify location of the report
        try {
            extentSparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        extent=new ExtentReports();

        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environemnt","QA");
        extent.setSystemInfo("user","Bhagya Peiris");

        extentSparkReporter.config().setDocumentTitle("Automation Report");
        extentSparkReporter.config().setReportName("Functional Testing");
        //extentSparkReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr)
    {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // Test is passed
    }

    public void onTestFailure(ITestResult tr) {

        String exceptionMsg = Arrays.toString(tr.getThrowable().getStackTrace());
        logger=extent.createTest(tr.getName()); // create new test in report
        String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";

        logger.addScreenCaptureFromPath("../Screenshots/"+tr.getName()+".png")
                .fail("<details><summary><b><font color='red'> "
                        + "Exception Occured. Click here to see more details" + "</font></b></summary>" +
                        exceptionMsg.replaceAll("," , "</br>")
                        + "</details></br></br> Screenshot is below:" ,
                        MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/"+tr.getName()+".png").build());

    }

    public void onTestSkipped(ITestResult tr)
    {
        logger=extent.createTest(tr.getName()); // create new test in report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }

}
