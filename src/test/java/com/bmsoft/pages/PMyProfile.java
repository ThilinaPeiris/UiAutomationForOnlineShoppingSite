package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PMyProfile {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By nameTextbox = By.id("name");
    private By contactNoTextbox = By.id("contactno");
    private By updateBtn = By.name("update");

    public PMyProfile(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public String verifyTitle(){
        return driver.getTitle();
    }

    public void clearValuesInNameTextbox(){
        driver.findElement(nameTextbox).clear();
    }

    public void enterName(String name){
        driver.findElement(nameTextbox).sendKeys(name);
    }

    public void clearValuesInContactNoTextbox(){
        driver.findElement(contactNoTextbox).clear();
    }

    public void enterContactNo(String telNo){
        driver.findElement(contactNoTextbox).sendKeys(telNo);
    }

    public void clickUpdateButton(){
        driver.findElement(updateBtn).click();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public void clickOkAlert(){
        driver.switchTo().alert().accept();
    }

    public String invalidMsgValidation(){
        return driver.findElement(nameTextbox).getAttribute("validationMessage");
    }

    //To write the status to test file..
    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }

}
