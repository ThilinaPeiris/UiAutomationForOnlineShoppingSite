package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PBooks {
    private WebDriver driver;
    private CommonOp commonOpObj;

    private By books = By.xpath("//a[@href='category.php?cid=3']");
    private By productName = By.xpath("//h3/a[@href='product-details.php?pid=15']");
    private By image =By.xpath("//div[@class='single-product-gallery-item' and @id='slide1']");
    private By imageNext = By.xpath("//a[@class='lb-next']");
    private By cart = By.xpath("//a[contains(text(),'ADD TO CART')]");
    private By wishlistHeart = By.xpath("//i[@class='fa fa-heart']");

    public PBooks(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public void selectBooks() {
        driver.findElement(books).click();
    }

    public void selectProduct(){
        driver.findElement(productName).click();
    }

    public void selectImg(){
        driver.findElement(image).click();
    }

    public void clickImgNext() {
        for (int i = 2; i <= 4; i++) {
            driver.findElement(imageNext).click();
        }
    }

    public void addToCart(){
        driver.findElement(cart).click();
    }

    public void addToWishlist(){
        driver.findElement(wishlistHeart).click();
    }

    public String validateProductTitle(){
        return driver.getTitle();
    }
}
