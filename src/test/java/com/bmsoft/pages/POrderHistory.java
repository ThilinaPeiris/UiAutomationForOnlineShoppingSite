package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POrderHistory {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By orderHistorySideMenuBar = By.xpath("//a[@href='order-history.php']");
    private By orderFirstRecord = By.xpath("//*[@class=\"table table-bordered\"]/tbody/tr[1]");
    private By trackLink = By.linkText("Track");
    private By orderTrackingDetailWindow = By.className("fontpink2");


    public POrderHistory(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public String verifyTitle(){
        return driver.getTitle();
    }

    public void clickOrderHistorySideMenuBar(){
        driver.findElement(orderHistorySideMenuBar).click();
    }

    public void viewOrderRecord(){
        driver.findElement(orderFirstRecord).isDisplayed();
    }

    public void clickTrackLink(){
        String winHandleBefore = driver.getWindowHandle(); // Store the current window handle
        driver.findElement(trackLink).click();

        for(String winHandle : driver.getWindowHandles()){ // Switch to new window opened
            driver.switchTo().window(winHandle);
        }

        if(driver.getPageSource().contains("Order Tracking Details !")){
            System.out.println("Text is present");
        } else {
            System.out.println("Text is absent");
        }

        driver.close(); // Close the new window

        driver.switchTo().window(winHandleBefore); // Switch back to the original browser (first window)
    }

    //To write the status to test file..
    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }


}
