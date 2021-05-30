package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

}
