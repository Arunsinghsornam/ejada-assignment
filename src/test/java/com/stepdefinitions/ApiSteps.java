package com.stepdefinitions;

import com.api.ApiClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.*;

public class ApiSteps {

    private final ApiClient apiClient = new ApiClient();

    private ThreadLocal<Response> lastResponse = new ThreadLocal<>();
    private ThreadLocal<String> lastOrderId = new ThreadLocal<>();

    @Given("the Simple Books API is running")
    public void apiRunning() {
        Response response = apiClient.get("/status");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Given("I am authenticated with the API")
    public void authenticate() {
        String email = "test_" + UUID.randomUUID() + "@mail.com";

        Map<String, Object> body = new HashMap<>();
        body.put("clientName", "TestUser");
        body.put("clientEmail", email);

        Response response = apiClient.post("/api-clients", body);
        String token = response.jsonPath().getString("accessToken");

        apiClient.setAccessToken(token);
    }

    @When("I send a GET request to {string}")
    public void getRequest(String endpoint) {
        lastResponse.set(apiClient.get(endpoint));
    }

    @When("I send an authenticated GET request to {string}")
    public void authGet(String endpoint) {
        lastResponse.set(apiClient.getAuthenticated(endpoint));
    }

    @When("I create an order for book ID {int} with customer name {string}")
    public void createOrder(int bookId, String name) {
        Map<String, Object> body = new HashMap<>();
        body.put("bookId", bookId);
        body.put("customerName", name);

        Response res = apiClient.postAuthenticated("/orders", body);
        lastResponse.set(res);

        if (res.statusCode() == 201) {
            lastOrderId.set(res.jsonPath().getString("orderId"));
        }
    }

    @When("I update the order customer name to {string}")
    public void updateOrder(String name) {
        Map<String, Object> body = new HashMap<>();
        body.put("customerName", name);

        lastResponse.set(
                apiClient.patch("/orders/" + lastOrderId.get(), body)
        );
    }

    @When("I delete the order")
    public void deleteOrder() {
        lastResponse.set(
                apiClient.delete("/orders/" + lastOrderId.get())
        );
    }

    @Then("the response status code should be {int}")
    public void validateStatus(int code) {
        Assert.assertEquals(lastResponse.get().statusCode(), code);
    }
}