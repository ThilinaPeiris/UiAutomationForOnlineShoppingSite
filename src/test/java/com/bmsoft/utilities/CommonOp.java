package com.bmsoft.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonOp {

    private WebDriver driver;

    public CommonOp(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitUntilElementPresence(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitUntilElementClickable(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitUntilElementInvisibilityOf(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
    }

    public boolean waitUntilElementInvisibilityOfElementLocated(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public List<WebElement> waitUntilElementsvisibilityOf(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitUntilElementvisibilityOf(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilAlertIsPresent(long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /*public WebElement waitUntilElementClickableAndInvisibiltyOfLoader( By loader, By locator, long timeOut){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        if(wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loader)))){
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }else
            return null;
    }*/

    public void Sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
