package com.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.utils.ConfigReader;

import java.util.Map;


public class ApiClient {

    private final String baseUrl;
    private String accessToken;

    public ApiClient() {
        this.baseUrl = ConfigReader.getApiBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    
    private RequestSpecification baseRequest() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json");
    }

   
    private RequestSpecification authRequest() {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not set. Register client first.");
        }
        return baseRequest().header("Authorization", "Bearer " + accessToken);
    }

    // ===================== GET =====================

    public Response get(String endpoint) {
        return baseRequest().when().get(endpoint);
    }

    public Response getAuthenticated(String endpoint) {
        return authRequest().when().get(endpoint);
    }

    // ===================== POST =====================

    public Response post(String endpoint, Map<String, Object> body) {
        return baseRequest().body(body).when().post(endpoint);
    }

    public Response postAuthenticated(String endpoint, Map<String, Object> body) {
        return authRequest().body(body).when().post(endpoint);
    }

    // ===================== PATCH =====================

    public Response patch(String endpoint, Map<String, Object> body) {
        return authRequest().body(body).when().patch(endpoint);
    }

    // ===================== DELETE =====================

    public Response delete(String endpoint) {
        return authRequest().when().delete(endpoint);
    }

    // ===================== Token Management =====================

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean hasToken() {
        return accessToken != null && !accessToken.isEmpty();
    }
}
