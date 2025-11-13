package com.bazaarstores.stepDefinitions.LogInStepsDef;

import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class LoginApiSteps {

    private Response response;
    private String endpoint;
    private static String actualToken;

    @Given("API endpoint {string}")
    public void api_endpoint(String url) {
        // نقرأ رابط الـ API الأساسي من ملف configuration.properties
        String baseUrl = ConfigReader.getApiBaseUrl();
        endpoint = baseUrl + url;
        System.out.println("Testing endpoint: " + endpoint);
    }

    @When("user sends POST request with valid credentials")
    public void user_sends_post_request_with_valid_credentials() {
        response = given()
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"" + ConfigReader.getCustomerEmail() + "\", \"password\": \"" + ConfigReader.getDefaultPassword() + "\" }")
                .when()
                .post(endpoint);
    }

    @When("user sends POST request with invalid password")
    public void user_sends_post_request_with_invalid_password() {
        response = given()
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"" + ConfigReader.getCustomerEmail() + "\", \"password\": \"WrongPassword123\" }")
                .when()
                .post(endpoint);
    }

    @Then("response status code should be {int}")
    public void response_status_code_should_be(Integer statusCode) {
        assertEquals((int) statusCode, response.getStatusCode());
    }

    @Then("response should contain {string}")
    public void response_should_contain(String key) {
        assertTrue(response.getBody().asString().contains(key));
    }


    @Then("response body should contain {string}")
    public void response_body_should_contain(String message) {
        assertTrue(response.getBody().asString().contains(message));
    }

    @Then("print the actual token")
    public void print_the_actual_token() {
        actualToken = response.jsonPath().getString("authorisation.token");
        if (actualToken != null) {
            System.out.println("Actual Token: " + actualToken);
        } else {
            System.out.println("No token found in response!");
        }
    }

    public static String getActualToken() {
        return actualToken;
    }
}
