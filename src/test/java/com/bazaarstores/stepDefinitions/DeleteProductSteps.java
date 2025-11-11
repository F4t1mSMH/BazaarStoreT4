package com.bazaarstores.stepDefinitions;

import static io.restassured.RestAssured.given;
import com.bazaarstores.pages.ProductsPage;
import com.bazaarstores.utilities.ApiUtilities;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final Faker faker = new Faker();
    private Response response;

    @Given("a product is created successfully via API and its ID is available")
    public void aProductIsCreatedSuccessfullyViaAPIAndItsIDIsAvailable() {
        String uniqueProductName = "UI_Delete_Target_" + System.currentTimeMillis();
        MangerScenarioContext.productToVerifyName = uniqueProductName;

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", uniqueProductName);
        payload.put("price", 10.00);
        payload.put("sku", faker.regexify("[A-Z]{2}[0-9]{3}"));
        payload.put("stock", 100);
        payload.put("category_id", 1);

        payload.put("manufacturer", "Delete Test Inc");
        payload.put("description", "Product for deletion test.");
        payload.put("discount", 0.0);

        Response response = given(ApiUtilities.spec())
                .body(payload)
                .when()
                .post("/products/create");

        response.then().statusCode(201);

        MangerScenarioContext.productId = response.jsonPath().getInt("product.id");
        Assert.assertTrue("Precondition failed: Product ID not retrieved.", MangerScenarioContext.productId > 0);
        System.out.println("Precondition: Product created for UI deletion test with ID: " + MangerScenarioContext.productId);
    }

    @When("a DELETE request is sent to delete the product via API")
    public void aDELETERequestIsSentToDeleteTheProductViaAPI() {
        response = given(ApiUtilities.spec())
                .delete("/products/" + MangerScenarioContext.productId);

        response.prettyPrint();
    }

    @Then("the product is deleted successfully via API")
    public void theProductIsDeletedSuccessfullyViaAPI() {
        response.then().statusCode(200);
        Response getResponse = given(ApiUtilities.spec())
                .when()
                .get("/products/" + MangerScenarioContext.productId);
        getResponse.then().statusCode(404);
        System.out.println("Product ID " + MangerScenarioContext.productId + " successfully deleted and verified 404.");
    }


    @And("the Store Manager clicks the delete button for the product")
    public void theStoreManagerClicksTheDeleteButtonForTheProduct() {
        productsPage.clickDeleteButtonByName(MangerScenarioContext.productToVerifyName);
    }

    @When("the Store Manager confirms the deletion")
    public void theStoreManagerConfirmsTheDeletion() {
        productsPage.clickConfirmDeleteButton();
    }

    @When("the Store Manager cancels the deletion")
    public void theStoreManagerCancelsTheDeletion() {
        productsPage.clickCancelDeleteButton();
    }

    @Then("the product should not be visible on the Products page")
    public void theProductShouldNotBeVisibleOnTheProductsPage() {
        Assert.assertFalse("Product is still visible in the list after deletion.",
                productsPage.isProductDisplayed(MangerScenarioContext.productToVerifyName));
    }

    @Then("the product should remain visible on the Products page")
    public void theProductShouldRemainVisibleOnTheProductsPage() {
        Assert.assertTrue("Product was removed from the list despite cancellation.",
                productsPage.isProductDisplayed(MangerScenarioContext.productToVerifyName));
    }
}