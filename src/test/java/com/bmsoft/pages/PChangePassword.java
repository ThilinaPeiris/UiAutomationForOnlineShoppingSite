package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PChangePassword {
    private WebDriver driver;
    private CommonOp commonOpObj;

   // private By myAccountSideMenuBar = By.linkText("My Account");
 //   private By changePwdTab = By.cssSelector("a[href='#collapseTwo']");
   private By changePwdTab = By.xpath("//a[@href='#collapseTwo']");
    private By currentPwdTextBox = By.id("cpass");
    private By newPwdTextBox = By.id("newpass");
    private By confirmPwdTextBox = By.id("cnfpass");
    private By changeBtn = By.name("submit");

    public PChangePassword(WebDriver driver, CommonOp commonOpObj){
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

   /* public void clickMyAccountSideMenuBar(){
        driver.findElement(myAccountSideMenuBar).click();
    }*/

    public void clickChangePwdTab(){
        driver.findElement(changePwdTab).click();
    }

    public void enterCurrentPassword(String currentPwd){
        driver.findElement(currentPwdTextBox).sendKeys(currentPwd);
    }

    public void enterNewPassword(String newPwd){
        driver.findElement(newPwdTextBox).sendKeys(newPwd);
    }

    public void enterConfirmPassword(String confirmPwd){
        driver.findElement(confirmPwdTextBox).sendKeys(confirmPwd);
    }

    public void clickChangeButton(){
        driver.findElement(changeBtn).click();
    }

    public void clickOkAlert(){
        driver.switchTo().alert().accept();
    }

}
