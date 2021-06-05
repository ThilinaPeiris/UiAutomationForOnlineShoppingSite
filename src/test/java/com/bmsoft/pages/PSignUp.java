package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PSignUp {

    private WebDriver driver;
    private CommonOp commonOpObj;

    //Constructor that will be automatically called as soon as the object of the class is created
    public PSignUp(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    //locators for Full Name, Email Address, Contact No., Password, Confirm Password & SignUp button
    private By fullName = By.id("fullname");
    private By emailAddress = By.id("email");
    private By contactNo = By.id("contactno");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmpassword");
    private By signUp = By.id("submit");

    //method to enter full name
    public void enterFullName(String fname){
        driver.findElement(fullName).sendKeys(fname);
    }

    //method to enter email
    public void enterEmail(String email){
        driver.findElement(emailAddress).sendKeys(email);
    }

    //method to enter contact number
    public void enterContactNo(String cnumber){
        driver.findElement(contactNo).sendKeys(cnumber);
    }

    //method to enter password
    public void enterPword(String pword){
        driver.findElement(password).sendKeys(pword);
    }

    //method to enter confirm password
    public void enterConfirmPword(String cpassword){
        driver.findElement(confirmPassword).sendKeys(cpassword);
    }

    //method to enter click signup button
    public void clicksignup(){
        driver.findElement(signUp).click();
    }

    //method to verify please fill... for empty full name field
    public String fullNamePleaseFillMsgVerification(){
        return driver.findElement(fullName).getAttribute("validationMessage");
    }

    //method to verify please fill... for email empty address field
    public String emailPleaseFillMsgVerification(){
        return driver.findElement(emailAddress).getAttribute("validationMessage");
    }

    //method to verify please fill... for empty contact no field
    public String contactNoPleaseFillMsgVerification(){
        return driver.findElement(contactNo).getAttribute("validationMessage");
    }

    //method to verify please fill... for empty password field
    public String passwordPleaseFillMsgVerification(){
        return driver.findElement(password).getAttribute("validationMessage");
    }

    //method to verify please fill... for confirm empty password field
    public String cPasswordPleaseFillMsgVerification(){
        return driver.findElement(confirmPassword).getAttribute("validationMessage");
    }

    //to write the status to test file..
    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }


}
