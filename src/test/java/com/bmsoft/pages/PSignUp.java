package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PSignUp {

    private WebDriver driver;
    private CommonOp commonOpObj;

    private By fullName = By.id("fullname");
    private By emailAddress = By.id("email");
    private By contactNo = By.id("contactno");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmpassword");
    private By signUp = By.id("submit");

    public PSignUp(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void enterFullName(String fname){
        driver.findElement(fullName).sendKeys(fname);
    }

    public void enterEmail(String email){
        driver.findElement(emailAddress).sendKeys(email);
    }

    public void enterContactNo(String cnumber){
        driver.findElement(contactNo).sendKeys(cnumber);
    }

    public void enterPword(String pword){
        driver.findElement(password).sendKeys(pword);
    }

    public void enterConfirmPword(String cpassword){
        driver.findElement(confirmPassword).sendKeys(cpassword);
    }

    public void clicksignup(){
        driver.findElement(signUp).click();
    }

    public String fullNamePleaseFillMsgVerification(){
        return driver.findElement(fullName).getAttribute("validationMessage");
    }

    public String emailPleaseFillMsgVerification(){
        return driver.findElement(emailAddress).getAttribute("validationMessage");
    }

    public String contactNoPleaseFillMsgVerification(){
        return driver.findElement(contactNo).getAttribute("validationMessage");
    }

    public String passwordPleaseFillMsgVerification(){
        return driver.findElement(password).getAttribute("validationMessage");
    }

    public String cPasswordPleaseFillMsgVerification(){
        return driver.findElement(confirmPassword).getAttribute("validationMessage");
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
