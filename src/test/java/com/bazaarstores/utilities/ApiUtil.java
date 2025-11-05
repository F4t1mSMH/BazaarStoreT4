package com.bazaarstores.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiUtil {
    
    private static String token;

    static {
        RestAssured.baseURI = ConfigReader.getApiBaseUrl();
    }

    public static RequestSpecification getRequestSpec() {
        return given()
                .contentType("application/json")
                .accept("application/json");
    }

    public static RequestSpecification getAuthRequestSpec() {
        return getRequestSpec()
                .header("Authorization", "Bearer " + token);
    }

    public static void setToken(String authToken) {
        token = authToken;
    }

    public static String getToken() {
        return token;
    }

    // Login via API and get token
    public static String loginAndGetToken(String email, String password) {
        String requestBody = String.format(
            "{\"email\":\"%s\", \"password\":\"%s\"}", 
            email, password
        );

        Response response = getRequestSpec()
                .body(requestBody)
                .post("/login");

        if (response.statusCode() == 200) {
            token = response.jsonPath().getString("authorisation.token");
            return token;
        }
        return null;
    }

    // Generic GET request
    public static Response get(String endpoint) {
        return getAuthRequestSpec().get(endpoint);
    }

    // Generic POST request
    public static Response post(String endpoint, String body) {
        return getAuthRequestSpec().body(body).post(endpoint);
    }

    // Generic PUT request
    public static Response put(String endpoint, String body) {
        return getAuthRequestSpec().body(body).put(endpoint);
    }

    // Generic DELETE request
    public static Response delete(String endpoint) {
        return getAuthRequestSpec().delete(endpoint);
    }

    // Verify status code
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            throw new AssertionError(
                "Expected status code: " + expectedStatusCode + 
                " but got: " + response.statusCode()
            );
        }
    }

    // Get response value by JSON path
    public static String getResponseValue(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}