package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PPaymentPendingOrder {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By paymentPendingOrderSideMenuBar = By.xpath("//a[@href='pending-orders.php']");

    public PPaymentPendingOrder(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void clickPaymentPendingOrderSideMenuBar(){
        driver.findElement(paymentPendingOrderSideMenuBar).click();
    }

    public String verifyTitle(){
        return driver.getTitle();
    }

    //To write the status to test file..
    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
