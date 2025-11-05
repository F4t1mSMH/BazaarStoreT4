package com.bazaarstores.stepDefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApiSteps {


    @When("assert the registration via API")
    public void assertTheRegistrationViaAPI() {
        Response response = given(spec()).get("/users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("find{it.email=='" + email + "'}.name");
        String actualEmail = jsonPath.getString("find{it.email=='" + email + "'}.email");
        assertEquals(email, actualEmail);
        assertEquals(fullName, actualName);
    }


    @And("assert the negative registration via API using email {string}")
    public void assertTheNegativeRegistrationViaAPIUsingEmail(String email) {
        Response response = given(spec()).get("/users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.name"));
        assertNull(jsonPath.getString("find{it.email=='" + email + "'}.email"));
    }
}
