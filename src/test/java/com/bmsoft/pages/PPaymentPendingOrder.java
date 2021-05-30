package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
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
}
