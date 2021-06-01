package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PBooks {
    private WebDriver driver;
    private CommonOp commonOpObj;
    private By books = By.xpath("//a[@href='category.php?cid=3']");
    private By productName = By.xpath("//h3/a[@href='product-details.php?pid=15']");
    private By logIn = By.xpath("//a[@href='login.php']");
    private By email = By.name("email");
    private By password = By.name("password");
    private By logInBtn = By.name("login");
    private By image =By.xpath("//div[@class='single-product-gallery-item' and @id='slide1']");
    private By imageNext = By.xpath("//a[@class='lb-next']");
    private By cart = By.xpath("//a[contains(text(),'ADD TO CART')]");
    private By wishlistHeart = By.xpath("//i[@class='fa fa-heart']");
    private By searchField = By.xpath("//input[@class='search-field']");


    public PBooks(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }
/*
    //click login link
    public void clickLogIn(){
        driver.findElement(logIn).click();
    }
    //login using valid credentials
    public void login(String em, String pwd){
        driver.findElement(email).sendKeys(em);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(logInBtn).click();
    }

 */
    //select Book category
    public void selectBooks() {
        driver.findElement(books).click();
    }
    //select book
    public void selectProduct(){
        driver.findElement(productName).click();
    }
    public void selectImg(){
        driver.findElement(image).click();
    }
    //starts with the second img so that's why
    public void clickImgNext() {
        for (int i = 2; i <= 4; i++) {
            driver.findElement(imageNext).click();
        }

    }
    //T08
    public void addToCart(){
        driver.findElement(cart).click();
    }
    //T09
    public void addToWishlist(){
        driver.findElement(wishlistHeart).click();
    }

    public void searchProduct(){
        driver.findElement(searchField).sendKeys("");
    }

    public String validateProductTitle(){
        return driver.getTitle();
    }


}
