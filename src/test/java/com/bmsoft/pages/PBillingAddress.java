package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PBillingAddress {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By shippingBillingAddressSideMenuBar = By.xpath("//a[@href='bill-ship-addresses.php']");
    private By billingAddressTextbox = By.name("billingaddress");
    private By billingStateTextbox = By.id("bilingstate");
    private By billingCityTextbox = By.id("billingcity");
    private By billingPincodeTextbox = By.id("billingpincode");
    private By updateBtn = By.name("update");

    public PBillingAddress(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void clickShippingBillingAddressSideMenuBar(){
        driver.findElement(shippingBillingAddressSideMenuBar).click();
    }

    public void enterBillingAddress(String billingAddress){
        driver.findElement(billingAddressTextbox).clear();
        driver.findElement(billingAddressTextbox).sendKeys(billingAddress);
    }

    public void enterBillingState(String billingState){
        driver.findElement(billingStateTextbox).clear();
        driver.findElement(billingStateTextbox).sendKeys(billingState);
    }

    public void enterBillingCity(String billingCity){
        driver.findElement(billingCityTextbox).clear();
        driver.findElement(billingCityTextbox).sendKeys(billingCity);
    }

    public void enterBillingPincode(String billingPincode){
        driver.findElement(billingPincodeTextbox).clear();
        driver.findElement(billingPincodeTextbox).sendKeys(billingPincode);
    }

    public void clickUpdateButton(){
        driver.findElement(updateBtn).click();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public void clickOkAlert(){
        driver.switchTo().alert().accept();
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }
}
