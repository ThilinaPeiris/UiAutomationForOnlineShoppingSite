package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PForgotPW {
    private WebDriver driver;
    private CommonOp commonOpObj;

    //Constructor that will be automatically called as soon as the object of the class is created
    public PForgotPW(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    //locators for Email Address, Contact Number, Password, Confirm Password & Change Button
    private By emailAddress = By.name("email");
    private By contactNo = By.name("contact");
    private By password = By.id("password");
    private By confirmPassword = By.name("confirmpassword");
    private By changebtn = By.name("change");

    //method to enter email Address
    public void enterEmailAddress(String email){
        driver.findElement(emailAddress).sendKeys(email);
    }

    //method to enter contact number
    public void enterContactNo(String contactno){
        driver.findElement(contactNo).sendKeys(contactno);
    }

    //methods to enter password
    public void enterPassword(String passwd){
        driver.findElement(password).sendKeys(passwd);
    }

    //method to enter confirm password
    public void enterConfirmPassword(String cpasswd){
        driver.findElement(confirmPassword).sendKeys(cpasswd);
    }

    //method to click change button
    public void clickChangeBtn(){
        driver.findElement(changebtn).click();
    }
}
