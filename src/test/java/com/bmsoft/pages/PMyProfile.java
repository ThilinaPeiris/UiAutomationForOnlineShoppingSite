package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PMyProfile {
    private WebDriver driver;
    private CommonOp commonOpObj;

 //   private By myAccountSideMenuBar = By.linkText("My Account");
    private By nameTextbox = By.id("name");
    private By contactNoTextbox = By.id("contactno");
    private By updateBtn = By.name("update");

    public PMyProfile(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

   /* public void clickMyAccountSideMenuBar(){
        driver.findElement(myAccountSideMenuBar).click();
    }*/

    public void clearValuesInNameTextbox(){
        driver.findElement(nameTextbox).clear();
    }

    public void setName(String name){
        driver.findElement(nameTextbox).sendKeys(name);
    }

    public void clearValuesInContactNoTextbox(){
        driver.findElement(contactNoTextbox).clear();
    }

    public void setContactNo(String telNo){
        driver.findElement(contactNoTextbox).sendKeys(telNo);
    }

    public void clickUpdateButton(){
        driver.findElement(updateBtn).click();
    }

    public void clickOkAlert(){
        driver.switchTo().alert().accept();
    }
}
