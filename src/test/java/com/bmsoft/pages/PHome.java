package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PHome {

    private WebDriver driver;
    private CommonOp commonOpObj;

    public PHome(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    private By loginLink = By.linkText("Login");
    private By logoutLink = By.linkText("Logout");

    public void clickOnLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void clickOnLogoutLink(){
        driver.findElement(logoutLink).click();
    }

    public boolean islogoutLinkIsDisplaying(){
        return commonOpObj.waitUntilElementvisibilityOf(logoutLink, 5).isDisplayed();
    }

}
