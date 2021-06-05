package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PHome {

    private WebDriver driver;
    private CommonOp commonOpObj;

    private By loginBtn = By.xpath("//a[@href='login.php']");
    private By myAccount = By.xpath("//a[@href='my-account.php']");
    private By trackOrder = By.xpath("//span[contains(text(),'Track Order')]");
    private By home = By.xpath("//a[contains(text(),'Home')]");
    private By searchField = By.xpath("//input[@class='search-field']");
    private By submitBtn = By.xpath("//button[@type='submit']");
    private By leftNavBook = By.xpath("//nav/ul/li/a[@href='category.php?cid=3']");
    private By randomProductAddToCart = By.xpath("//a[@href=\"index.php?page=product&action=add&id=1\"]");

    public PHome(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void clickLoginbtn() {
        driver.findElement(loginBtn).click();
    }

    public void clickTrackOrderBtn(){
        driver.findElement(trackOrder).click();
    }

    public String navigateOrderTrackPage(){
        return driver.getTitle();
    }

    public void clickMyAccountTab(){
        driver.findElement(myAccount).click();
    }

    public void selectHome() {
        driver.findElement(home).click();
    }

    public void clickOnRandomProductAddtoCartLink(){
        WebElement randomProduct = driver.findElement(randomProductAddToCart);
        Actions actions = new Actions(driver);
        actions.moveToElement(randomProduct).click().build().perform();
        commonOpObj.Sleep(500);
    }

    public void searchField(String value){
        driver.findElement(searchField).sendKeys(value);
        driver.findElement(submitBtn).click();
    }

    public void leftNavigation(){
        driver.findElement(leftNavBook).click();
    }

    public String validateProductTitle(){
        return driver.getTitle();
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
