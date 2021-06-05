package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PLogin {

    private final WebDriver driver;
    private final CommonOp commonOpObj;

    private By emailAddress = By.name("email");
    private By password = By.name("password");
    private By loginBtn = By.name("login");
    private By forgotPW = By.xpath("//a[contains(text(),'Forgot your Password?')]");

    public PLogin(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void enterEmailAddress(String uemail){
        driver.findElement(emailAddress).sendKeys(uemail);
    }

    public void enterPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPW).click();
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
