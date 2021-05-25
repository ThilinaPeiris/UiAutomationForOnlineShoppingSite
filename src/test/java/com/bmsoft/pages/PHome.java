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

    private By bmwCheckbox = By.id("bmwcheck");
    private By benzCheckbox = By.id("benzcheck");
    private By hondaCheckbox = By.id("hondacheck");
    private By benzRadioBtn = By.id("benzradio");
    private By selectCarselect = By.id("carselect");
    private By selectmultiVals = By.id("multiple-select-example");
    private By openNewWindowBtn = By.id("openwindow");
    private By openNewTabBtn = By.id("opentab");
    private By nameInputField = By.id("name");
    private By alertbtn = By.id("alertbtn");
    private By confirmbtn = By.id("confirmbtn");
    private By mousehoverdropdown = By.id("mousehover");
    private By mousehoverReloadBtn = By.xpath("//a[contains(text(),'Reload')]");
    private By termsOfUSeLink = By.partialLinkText("Terms of Use");

    public PHome(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void clickOnBmwCheckbox() {
        driver.findElement(bmwCheckbox).click();
    }

    public void clickOnBenzCheckbox() {
        driver.findElement(benzCheckbox).click();
    }

    public void clickOnHondaCheckbox() {
        driver.findElement(hondaCheckbox).click();
    }

    public void clickOnBenzradiobtn() {
        driver.findElement(benzRadioBtn).click();
    }

    public void setSelectCarselectHondaVal() {
        Select selectcarselectdrpdown = new Select(driver.findElement(selectCarselect));
        selectcarselectdrpdown.selectByVisibleText("Honda");
    }

    public void setSelectmultiVals() {
        Select selectMultipleValsDropDown = new Select(driver.findElement(selectmultiVals));
        selectMultipleValsDropDown.selectByValue("orange");
        selectMultipleValsDropDown.selectByValue("peach");
    }

    public void clickOnOpenWindowBtn() {
        driver.findElement(openNewWindowBtn).click();
    }

    public void clickOnNewTabBtn() {
        driver.findElement(openNewTabBtn).click();
    }

    public void setNameInputField(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }

    public void clickOnAlertBtn() {
        driver.findElement(alertbtn).click();
    }

    public void clickOnCOnfirmBtn() {
        driver.findElement(confirmbtn).click();
    }

    public WebElement getMouseHoverOverEle() {
        return driver.findElement(mousehoverdropdown);
    }

    public WebElement getMouseHoverOverReloadEle() {
        return driver.findElement(mousehoverReloadBtn);
    }

    public void clickOnTermsOfUseLink() {
        driver.findElement(termsOfUSeLink).click();
    }
}
