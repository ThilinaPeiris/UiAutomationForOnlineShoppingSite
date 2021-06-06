package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import com.bmsoft.utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class PMyCart {
    private final WebDriver driver;
    private final CommonOp commonOpObj;

    private final By billingAddress = By.name("billingaddress");
    private final By bilingstate = By.name("bilingstate");
    private final By billingcity = By.name("billingcity");
    private final By billingpincode = By.name("billingpincode");
    private final By shippingaddress = By.name("shippingaddress");
    private final By shippingstate = By.name("shippingstate");
    private final By shippingcity = By.name("shippingcity");
    private final By shippingpincode = By.name("shippingpincode");
    private final By ordersubmit = By.name("ordersubmit");
    private final By homePageLink = By.xpath("//a[@href='index.php' and text()='Home']");
    private By shoppingCartTable = By.xpath("//form[@name=\"cart\"]/table/tbody/tr");

    public PMyCart(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void setBillingAddress(String billingAddressVal){
        driver.findElement(billingAddress).sendKeys(billingAddressVal);
    }

    public void setbilingstate(String bilingstateVal){
        driver.findElement(bilingstate).sendKeys(bilingstateVal);
    }

    public void setbillingcity(String billingcityVal){
        driver.findElement(billingcity).sendKeys(billingcityVal);
    }

    public void setbillingpincode(String billingpincodeVal){
        driver.findElement(billingpincode).sendKeys(billingpincodeVal);
    }

    public void setShippingAddress(String shippingAddressVal){
        driver.findElement(shippingaddress).sendKeys(shippingAddressVal);
    }

    public void setShippingstate(String shippingstateVal){
        driver.findElement(shippingstate).sendKeys(shippingstateVal);
    }

    public void setShippingcity(String shippingcityVal){
        driver.findElement(shippingcity).sendKeys(shippingcityVal);
    }

    public void setShippingpincode(String shippingpincodeVal){
        driver.findElement(shippingpincode).sendKeys(shippingpincodeVal);
    }

    public void clickOnordersubmit(){
        driver.findElement(ordersubmit).click();
    }

    public void clickOnHomeLink(){
        driver.findElement(homePageLink).click();
    }

    public void setTestResult(int row, int col){
        ExcelUtil.rowNumber = row;
        ExcelUtil.columnNumber = col;
    }

    public Map<String, List<String>> getAllTheSHoppingCartDetails(){
        Map<String,List<String>> tableData = new HashMap<>();

        List<WebElement> shoppingCartTableRows = driver.findElements(shoppingCartTable);

        for(int i=0; i<shoppingCartTableRows.size(); i++){
            String grandTotal = shoppingCartTableRows.get(i).findElement(By.cssSelector(".cart-grand-total-price")).getText().trim();
            String productName = shoppingCartTableRows.get(i).findElement(By.cssSelector("h4.cart-product-description a")).getText().trim();
            String quantity = shoppingCartTableRows.get(i).findElement(By.xpath("td[4]/descendant::input")).getAttribute("value").trim();
            String pricePerUnit = shoppingCartTableRows.get(i).findElement(By.xpath("td[5]/span")).getText().trim();
            String shippingPrice = shoppingCartTableRows.get(i).findElement(By.xpath("td[6]/span")).getText().trim();

            tableData.put(Integer.toString(i), new ArrayList<>(Arrays.asList(productName, quantity, pricePerUnit, shippingPrice, grandTotal)));
        }
        return tableData;
    }
}
