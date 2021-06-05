package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PPaymentMethod {

    private WebDriver driver;
    private CommonOp commonOpObj;

    private By codInputRadioBtn = By.xpath("//input[@value='COD']");
    private By intentBankingInputRadioBtn = By.xpath("//input[@value='Internet Banking']");
    private By cardInputRadioBtn = By.xpath("//input[@value='Debit / Credit card']");

    public PPaymentMethod(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public boolean iscodInputRadioBtnDisplayed(){
        return driver.findElement(codInputRadioBtn).isDisplayed();
    }

    public boolean isintentBankingInputRadioBtnDisplayed(){
        return driver.findElement(intentBankingInputRadioBtn).isDisplayed();
    }

    public boolean iscardInputRadioBtnDisplayed(){
        return driver.findElement(cardInputRadioBtn).isDisplayed();
    }
}
