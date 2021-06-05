package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PForgotPW {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By emailAddress = By.name("email");
    private By contactNo = By.name("contact");
    private By password = By.id("password");
    private By confirmPassword = By.name("confirmpassword");
    private By changebtn = By.name("change");

    public PForgotPW(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void enterEmailAddress(String email){
        driver.findElement(emailAddress).sendKeys(email);
    }

    public void enterContactNo(String contactno){
        driver.findElement(contactNo).sendKeys(contactno);
    }

    public void enterPassword(String passwd){
        driver.findElement(password).sendKeys(passwd);
    }

    public void enterConfirmPassword(String cpasswd){
        driver.findElement(confirmPassword).sendKeys(cpasswd);
    }

    public void clickChangeBtn(){
        driver.findElement(changebtn).click();
    }

    public String emailPleaseFillMsgVerification(){
        return driver.findElement(emailAddress).getAttribute("validationMessage");
    }

    public String contactNoPleaseFillMsgVerification(){
        return driver.findElement(contactNo).getAttribute("validationMessage");
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
