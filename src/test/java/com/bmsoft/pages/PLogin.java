package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PLogin {

    private final WebDriver driver;
    private final CommonOp commonOpObj;

    public PLogin(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    //locators for emailAddress, password, loginBtn & ForgotPassword
    private By emailAddress = By.name("email");
    private By password = By.name("password");
    private By loginBtn = By.name("login");
    private By forgotPW = By.xpath("//a[contains(text(),'Forgot your Password?')]");
    private final By usernamePasswordValidationMsg = By.xpath("//span[contains(text(),'Invalid email id or Password')]");


    //methods to enter email Address
    public void enterEmailAddress(String uemail){
        driver.findElement(emailAddress).sendKeys(uemail);
    }

    //method to enter password
    public void enterPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }

    //method to click login button
    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    //method to click forgot your password link
    public void clickForgotPassword() {
        driver.findElement(forgotPW).click();
    }
/*
    public String getErrorMessageText(){
        return lblErrorMessage.getText();
    }*/


    public boolean isusernamePasswordValidationMsgSHowing(){
        return commonOpObj.waitUntilElementvisibilityOf(usernamePasswordValidationMsg, 5).isDisplayed();
    }

    //to write the status to test file..
    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }

}
