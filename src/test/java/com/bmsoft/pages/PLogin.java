package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
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

    private final By usernameField = By.id("exampleInputEmail1");
    private final By passwordField = By.id("exampleInputPassword1");
    private final By loginBtn = By.xpath("//button[@name='login']");
    private final By usernamePasswordValidationMsg = By.xpath("//span[contains(text(),'Invalid email id or Password')]");

    private void setUsernameField(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    private void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterUserNameAndPassword(String username, String passwrod){
        setUsernameField(username);
        setPasswordField(passwrod);
    }

    public void clickOnLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    /*public void login(){
        enterUserNameAndPassword();
        clickOnLoginBtn();
    }*/

    public boolean isusernamePasswordValidationMsgSHowing(){
        return commonOpObj.waitUntilElementvisibilityOf(usernamePasswordValidationMsg, 5).isDisplayed();
    }

}
