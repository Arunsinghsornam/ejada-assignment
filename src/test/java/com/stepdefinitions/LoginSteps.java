package com.stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.DriverManager;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigate();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        inventoryPage = new InventoryPage(DriverManager.getDriver());

        Assert.assertTrue(
                inventoryPage.isDisplayed(),
                "User NOT redirected. URL: " + DriverManager.getDriver().getCurrentUrl()
        );
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        inventoryPage = new InventoryPage(DriverManager.getDriver());

        String actual = DriverManager.getDriver().getTitle();

        Assert.assertTrue(
                actual.contains(expectedTitle),
                "Expected title: " + expectedTitle + " but got: " + actual
        );
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedError) {
        loginPage = new LoginPage(DriverManager.getDriver());

        String actualError = loginPage.getError();

        Assert.assertTrue(
                actualError.contains(expectedError),
                "Expected: " + expectedError + " but got: " + actualError
        );
    }
}