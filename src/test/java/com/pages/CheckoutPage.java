package com.pages;

import org.openqa.selenium.*;
import com.utils.WaitHelper;

public class CheckoutPage {

    private WebDriver driver;
    private WaitHelper wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zip = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By successMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    public void fillDetails(String f, String l, String z) {
        wait.waitForVisibility(firstName).sendKeys(f);
        wait.waitForVisibility(lastName).sendKeys(l);
        wait.waitForVisibility(zip).sendKeys(z);
    }

    public void continueCheckout() {
        wait.waitForClickability(continueBtn).click();
    }

    public void finish() {
        wait.waitForClickability(finishBtn).click();
    }

    public String getSuccessMsg() {
        return wait.waitForVisibility(successMsg).getText();
    }
}