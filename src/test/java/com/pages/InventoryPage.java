package com.pages;

import org.openqa.selenium.*;
import com.utils.WaitHelper;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private WaitHelper wait;

    private By items = By.cssSelector(".inventory_item");
    private By cart = By.className("shopping_cart_link");
    private By title = By.className("title");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    public boolean isDisplayed() {
        wait.waitForUrlContains("inventory");
        return wait.waitForVisibility(title).isDisplayed();
    }

    public String addFirstProduct() {
        List<WebElement> list = wait.waitForAllElements(items);
        WebElement first = list.get(0);

        String name = first.findElement(By.className("inventory_item_name")).getText();
        first.findElement(By.tagName("button")).click();

        return name;
    }

    public void goToCart() {
        wait.waitForClickability(cart).click();
    }
}