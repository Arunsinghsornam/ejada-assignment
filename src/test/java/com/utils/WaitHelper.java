package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitHelper - Centralized explicit wait utility for robust element interactions.
 */
public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    public WaitHelper(WebDriver driver, int timeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitForAllElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public boolean waitForTextPresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean waitForUrlContains(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    public void clickWhenReady(WebElement element) {
        waitForClickability(element).click();
    }

    public void typeWhenReady(WebElement element, String text) {
        WebElement el = waitForVisibility(element);
        el.clear();
        el.sendKeys(text);
    }
}
