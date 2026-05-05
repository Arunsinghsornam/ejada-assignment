package com.stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.pages.CartPage;
import com.pages.CheckoutPage;
import com.pages.InventoryPage;
import com.utils.DriverManager;

public class OrderSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private String addedProductName;

    @And("I am on the inventory page")
    public void iAmOnTheInventoryPage() {
        inventoryPage = new InventoryPage(DriverManager.getDriver());

        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "Not on inventory page. URL: " + DriverManager.getDriver().getCurrentUrl()
        );
    }

    @When("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        inventoryPage = new InventoryPage(DriverManager.getDriver());
        addedProductName = inventoryPage.addFirstProduct();
    }

    @Then("the cart count should be {int}")
    public void theCartCountShouldBe(int expectedCount) {
        // Simplified validation: cart badge or cart page
        inventoryPage.goToCart();
        cartPage = new CartPage(DriverManager.getDriver());

        Assert.assertEquals(
                cartPage.getItemCount(),
                expectedCount,
                "Cart count mismatch"
        );
    }

    @And("I navigate to the cart")
    public void iNavigateToTheCart() {
        inventoryPage = new InventoryPage(DriverManager.getDriver());
        inventoryPage.goToCart();

        cartPage = new CartPage(DriverManager.getDriver());

        Assert.assertTrue(
                cartPage.getItemCount() > 0,
                "Cart page is empty or not loaded"
        );
    }

    @Then("the cart should contain {int} item")
    public void theCartShouldContainItem(int expectedCount) {
        cartPage = new CartPage(DriverManager.getDriver());

        Assert.assertEquals(
                cartPage.getItemCount(),
                expectedCount,
                "Cart item count mismatch"
        );
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage = new CartPage(DriverManager.getDriver());
        cartPage.checkout();

        checkoutPage = new CheckoutPage(DriverManager.getDriver());
    }

    @And("I fill in checkout information with first name {string} last name {string} and postal code {string}")
    public void iFillInCheckoutInformation(String firstName, String lastName, String postalCode) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.fillDetails(firstName, lastName, postalCode);
    }

    @And("I click continue")
    public void iClickContinue() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.continueCheckout();
    }

    @Then("I should be on the checkout overview page")
    public void iShouldBeOnTheCheckoutOverviewPage() {
        // Simple validation using URL
        String url = DriverManager.getDriver().getCurrentUrl();

        Assert.assertTrue(
                url.contains("checkout-step-two"),
                "Not on checkout overview page. URL: " + url
        );
    }

    @When("I click finish")
    public void iClickFinish() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        checkoutPage.finish();
    }

    @Then("the order should be confirmed with message {string}")
    public void theOrderShouldBeConfirmedWithMessage(String expectedMessage) {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());

        String actual = checkoutPage.getSuccessMsg();

        Assert.assertTrue(
                actual.contains(expectedMessage),
                "Expected: " + expectedMessage + " but got: " + actual
        );
    }
}