package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PFurniture {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By furnitureC = By.xpath("//a[@href='category.php?cid=5']");
    private By addtocartBtn = By.xpath("//a[@href='category.php?page=product&action=add&id=18']");
    private By addtoWishListBtn = By.xpath("//a[@href='category.php?pid=18&&action=wishlist']");

    public PFurniture(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void selectFurniture() {
        driver.findElement(furnitureC).click();
    }

    public void addtocartBtn() {
        driver.findElement(addtocartBtn).click();
    }

    public void addtoWishListBtn(){
        driver.findElement(addtoWishListBtn).click();
    }

    public String validateProductTitle() {
        return driver.getTitle();
    }
}
