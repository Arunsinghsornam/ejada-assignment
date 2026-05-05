package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utils.WaitHelper;

public class LoginPage {

    private WebDriver driver;
    private WaitHelper wait;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    public void navigate() {
        driver.get(com.utils.ConfigReader.getBaseUrl());
    }

    public void login(String user, String pass) {
        wait.waitForVisibility(username).sendKeys(user);
        wait.waitForVisibility(password).sendKeys(pass);
        wait.waitForClickability(loginBtn).click();
    }

    public String getError() {
        return wait.waitForVisibility(errorMsg).getText();
    }
}