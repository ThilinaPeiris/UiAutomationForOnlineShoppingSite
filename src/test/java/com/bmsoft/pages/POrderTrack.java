package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POrderTrack {

    private WebDriver driver;
    private CommonOp commonOpObj;

    private By orderid = By.name("orderid");
    private By registeredemail = By.name("email");
    private By track = By.name("submit");

    public POrderTrack(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void enterOrderId(String id){
        driver.findElement(orderid).sendKeys(id);
    }

    public void enterRegisteredEmail(String registeredmail){
        driver.findElement(registeredemail).sendKeys(registeredmail);
    }

    public void clickTrackBtn(){
        driver.findElement(track).click();
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
