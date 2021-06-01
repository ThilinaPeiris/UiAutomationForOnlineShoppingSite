package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PShippingAddress {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By shippingBillingAddressSideMenuBar = By.xpath("//a[@href='bill-ship-addresses.php']");
    private By shippingAddressTab = By.xpath("//a[@href='#collapseTwo']");
    private By shippingAddressTextbox = By.name("shippingaddress");
    private By shippingStateTextbox = By.id("shippingstate");
    private By shippingCityTextbox = By.id("shippingcity");
    private By shippingPincodeTextbox = By.id("shippingpincode");
    private By updateBtn = By.name("shipupdate");

    public PShippingAddress(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void clickShippingBillingAddressSideMenuBar(){
        driver.findElement(shippingBillingAddressSideMenuBar).click();
    }

    public void clickShippingAddressTab(){
        driver.findElement(shippingAddressTab).click();
    }

    public void enterShippingAddress(String shippingAddress){
        driver.findElement(shippingAddressTextbox).clear();
        driver.findElement(shippingAddressTextbox).sendKeys(shippingAddress);
    }

    public void enterShippingState(String shippingState){
        driver.findElement(shippingStateTextbox).clear();
        driver.findElement(shippingStateTextbox).sendKeys(shippingState);
    }

    public void enterShippingCity(String shippingCity){
        driver.findElement(shippingCityTextbox).clear();
        driver.findElement(shippingCityTextbox).sendKeys(shippingCity);
    }

    public void enterShippingPincode(String shippingPincode){
        driver.findElement(shippingPincodeTextbox).clear();
        driver.findElement(shippingPincodeTextbox).sendKeys(shippingPincode);
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
