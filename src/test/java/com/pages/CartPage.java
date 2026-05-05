package com.pages;

import org.openqa.selenium.*;
import com.utils.WaitHelper;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WaitHelper wait;

    private By cartItems = By.className("cart_item");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    public int getItemCount() {
        return wait.waitForAllElements(cartItems).size();
    }

    public void checkout() {
        wait.waitForClickability(checkoutBtn).click();
    }
}