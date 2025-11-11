package com.bazaarstores.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtilities {

    public static RequestSpecification spec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }


    private static String getToken() {
        Map payload = new HashMap();
        payload.put("email", ConfigReader.getAdminEmail());
        payload.put("password", ConfigReader.getDefaultPassword());
        Response response = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .post(ConfigReader.getApiBaseUrl() + "/login");
        if (response.statusCode() == 200) {
            System.out.println("API Login successful. Token retrieved.");
            return response.jsonPath().getString("authorisation.token");
        } else {
            // If login fails, print the response body to debug credentials
            System.err.println("API LOGIN FAILED!");
            System.err.println("Login Status Code: " + response.statusCode());
            response.prettyPrint();
            // Throwing a runtime exception stops the test immediately
            throw new RuntimeException("API login failed during getToken(). Check credentials/config.");
        }

    }

}
