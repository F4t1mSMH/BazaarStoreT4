package com.bazaarstores.stepDefinitions.CustomerStepsDef;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bazaarstores.stepDefinitions.RegistrationSteps.email;
import static com.bazaarstores.stepDefinitions.RegistrationSteps.fullName;
import static com.bazaarstores.utilities.ApiUtilities.spec;
import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CustomerApiSteps {

    AllPages allPages = new AllPages();

    @When("assert the Customer can see products via API")
    public void assert_the_customer_can_see_products_via_api() {
        Response response = given(spec()).get("/products");
        response.prettyPrint();

        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> products = jsonPath.getList("");


        // Loop through each product to check required fields
        for (Map<String, Object> product : products) {
            assertNotNull(product.get("name").toString(), "Product name is missing");
            assertNotNull(product.get("price").toString(), "Product price is missing");
//            assertNotNull(product.get("description").toString(), "Product description is missing");
            assertNotNull(product.get("id").toString(), "Product id is missing");


        }

    }


    @When("assert the customer can view specific product with its details")
    public void assert_the_customer_can_view_specific_product_with_its_details() {
        Response response = given(spec()).get("/products/49");
        response.prettyPrint();

        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> product = response.jsonPath().getMap("");




            assertNotNull(product.get("name").toString(), "Product name is missing");
            assertNotNull(product.get("price").toString(), "Product price is missing");
//            assertNotNull(product.get("description").toString(), "Product description is missing");
            assertNotNull(product.get("id").toString(), "Product id is missing");


    }
    @When("assert Customer sends a POST request to mark product with id {string} as favorite")
    public void assert_customer_sends_a_post_request_to_mark_product_with_id_as_favorite(String productId) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_id", productId);

        Response response = given(spec())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/favorites/create");

        response.prettyPrint();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Product added to favorites successfully.");
        } else if (statusCode == 400 && response.jsonPath().getString("error").contains("Product is already in favorites.")) {
            System.out.println("Product already in favorites so ....");
        }
    }
    @When("assert Customer sends a POST request to mark product with testdata as favorite")
    public void assert_customer_sends_a_post_request_to_mark_product_with_testdata_as_favorite() throws IOException {
        String requestBody2 = Files.readString(Paths.get("src/test/resources/testdata/favoriteProduct.json"));

        Response response = given(spec())
                .contentType(ContentType.JSON)
                .body(requestBody2)
                .post("/favorites/create");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Product added to favorites successfully.");
        } else if (statusCode == 400 && response.jsonPath().getString("error").contains("Product is already in favorites.")) {
            System.out.println("Product already in favorites so ....");
        }
    }

    @When("assert Customer sends a GET request to retrieve all favorite products")
    public void assert_customer_sends_a_get_request_to_retrieve_all_favorite_products() {
        Response response = given(spec()).get("/favorites");
        response.prettyPrint();

        response.then().statusCode(200);
    }
    @When("assert Customer sends a DELETE request to remove product with id {string} from favorites")
    public void assert_customer_sends_a_delete_request_to_remove_product_with_id_from_favorites(String productId) {
        Response response = given(spec()).delete("/favorites/"+productId);
        response.prettyPrint();

        response.then().statusCode(200);
    }

}
