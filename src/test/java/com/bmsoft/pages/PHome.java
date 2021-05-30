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
    private By myAccount = By.xpath("//a[@href='my-account.php']");
    //Locator for login button
    private By loginBtn = By.xpath("//header/div[1]/div[1]/div[1]/div[1]/ul[1]/li[4]/a[1]");
    //private By LoginBtn = By.partialLinkText("Login");
    //Locator for logout button
    //private By logoutBtn = By.xpath("/html/body/header/div[1]/div/div/div[1]/ul/li[5]/a");
    private By logoutBtn = By.xpath("//header/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]/a[1]");
    //locator for trackOrder button
    private By trackOrder = By.xpath("//span[contains(text(),'Track Order')]");

    //Method to click login button
    public void clickLoginbtn() {
        driver.findElement(loginBtn).click();
    }
    //method to click logout button
    public void clickLogOutbtn(){
        driver.findElement(logoutBtn).click();
    }
    //Method to click click Track Order button
    public void clickTrackOrderBtn(){
        driver.findElement(trackOrder).click();
    }
    //method to ordertrack verification
    public String navigateOrderTrackPage(){
        return driver.getTitle();
    }

    public void clickOnLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void clickOnLogoutLink(){
        driver.findElement(logoutLink).click();
    }

    public boolean islogoutLinkIsDisplaying(){
        return commonOpObj.waitUntilElementvisibilityOf(logoutLink, 5).isDisplayed();
    }

    public void clickMyAccountTab(){
        driver.findElement(myAccount).click();
    }

    private By home = By.xpath("//a[contains(text(),'Home')]");
    private By logIn = By.xpath("//a[@href='login.php']");
    private By email = By.name("email");
    private By password = By.name("password");
    private By logInBtn = By.name("login");
    private By addtoCrtBtn = By.xpath("//div/a[@xpath='1']");
    private By searchField = By.xpath("//input[@class='search-field']");
    private By submitBtn = By.xpath("//button[@type='submit']");
    private By leftNavBook = By.xpath("//nav/ul/li/a[@href='category.php?cid=3']");

    //select Home category
    public void selectHome() {
        driver.findElement(home).click();
    }
    public void addtoCartBtn(){
        driver.findElement(addtoCrtBtn).click();
    }
    //T14 and T15
    public void searchField(String value){
        driver.findElement(searchField).sendKeys(value);
        driver.findElement(submitBtn).click();
    }
    //T16
    public void leftNavigation(){
        driver.findElement(leftNavBook).click();
    }


}
