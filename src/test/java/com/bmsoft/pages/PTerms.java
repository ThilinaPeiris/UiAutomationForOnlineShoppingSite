package com.bmsoft.pages;

import com.bmsoft.utilities.CommonOp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PTerms {

    private WebDriver driver;
    private CommonOp commonOpObj;

    public PTerms(WebDriver driver, CommonOp commonOpObj) {
        this.driver = driver;
        this.commonOpObj = commonOpObj;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
