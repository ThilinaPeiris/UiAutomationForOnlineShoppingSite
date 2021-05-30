package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POrderTrack {

    private WebDriver driver;
    private CommonOp commonOpObj;

    //Constructor that will be automatically called as soon as the object of the class is created
    public POrderTrack(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    //locators for Order id, Registered email & Track button
    private By orderid = By.name("orderid");
    private By registeredemail = By.name("email");
    private By track = By.name("submit");

    //method to enter order id
    public void enterOrderId(String id){
        driver.findElement(orderid).sendKeys(id);
    }

    //method to enter email
    public void enterRegisteredEmail(String registeredmail){
        driver.findElement(registeredemail).sendKeys(registeredmail);
    }

    //method to enter track button
    public void clickTrackBtn(){
        driver.findElement(track).click();
    }


}
